package com.eccard.starwarscharacters.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.InstantAppExecutors
import com.eccard.starwarscharacters.util.argumentCaptor
import com.eccard.starwarscharacters.util.getCharacterResponse
import com.eccard.starwarscharacters.util.getCharacterThatIsInFilm
import com.eccard.starwarscharacters.util.getFilmResponse
import com.eccard.starwarscharacters.util.mock
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.mock.Calls

@RunWith(JUnit4::class)
class RepositoryTest {

    private lateinit var repository : Repository
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val starWarsApi: StarWarsApi = mock(StarWarsApi::class.java)
    private val charactterDao: CharactterDao = mock(CharactterDao::class.java)
    private val filmDao : FilmDao = mock(FilmDao::class.java)


    @Before
    fun setUp() {
        repository = Repository(InstantAppExecutors(),starWarsApi,charactterDao,filmDao)
    }

    @Test
    fun syncWithApiWithSuccess(){

        val callCharacterResponse = getCharacterResponse()
        val callFilmResponse = getFilmResponse()

        `when`(starWarsApi.getCaracters()).thenReturn(Calls.response(callCharacterResponse))
        `when`(starWarsApi.getFilms()).thenReturn(Calls.response(callFilmResponse))

        `when`(charactterDao.getAllCharactters()).thenReturn(callCharacterResponse.items)

        val syncListenerMock = mock(Repository.SyncListener::class.java)
        repository.syncListener = syncListenerMock

        repository.syncWithApi()

        verify(charactterDao).insert(callCharacterResponse.items)

        verify(filmDao).insert(callFilmResponse.items)

        verify(syncListenerMock).onSynced()

    }

    @Test
    fun findByNameOrFilmWithEmptyQuery(){
        val emptyString = ""

        val characterInAdapterFormat =  getCharacterResponse().items.map { CharacterAdapterPojo(it,null)}
        `when`(charactterDao.getAllCharactersInAdapterFormat()).thenReturn(characterInAdapterFormat)

        val charactersLiveData = repository.findByNameOrFilm(emptyString)

        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        charactersLiveData.observeForever(observer)
        verifyNoMoreInteractions(filmDao)
        verifyNoMoreInteractions(starWarsApi)

        verify(observer).onChanged(characterInAdapterFormat)
    }

    @Test
    fun findByNameOrFilmWithString(){
        val query = "ana"

        val characterInAdapterFormat =  getCharacterResponse().items.map { CharacterAdapterPojo(it,null)}

        `when`(charactterDao.getCharacttersWithNameInAdapterFormat(query)).thenReturn(characterInAdapterFormat)
        `when`(filmDao.getFilmsFilteresbyName(query)).thenReturn(getFilmResponse().items)


        val listOfCharacersInFilm = getCharacterThatIsInFilm()
        `when`(charactterDao.getCharacttersByIds(ArgumentMatchers.anyList())).thenReturn(listOfCharacersInFilm)


        val charactersLiveData = repository.findByNameOrFilm(query)

        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        charactersLiveData.observeForever(observer)
        verifyNoMoreInteractions(starWarsApi)

        val characterFilteres = argumentCaptor<List<CharacterAdapterPojo>>()
        verify(observer).onChanged(characterFilteres.capture())
        MatcherAssert.assertThat(characterFilteres.value.size, `is`(5))

        MatcherAssert.assertThat(characterFilteres.value[0].foundFilm, IsNull.nullValue())
        MatcherAssert.assertThat(characterFilteres.value[0].charactter.id, `is`(0))
        MatcherAssert.assertThat(characterFilteres.value[0].charactter.name, `is`("Anakin Skywalker, Darth Vader"))

        MatcherAssert.assertThat(characterFilteres.value[1].foundFilm, IsNull.nullValue())
        MatcherAssert.assertThat(characterFilteres.value[1].charactter.id, `is`(2))
        MatcherAssert.assertThat(characterFilteres.value[1].charactter.name, `is`("Ben Solo, Kylo Ren"))

        MatcherAssert.assertThat(characterFilteres.value[2].foundFilm, `is`("Star Wars: Episode IV - A New Hope (1977), Star Wars: Episode V - The Empire Strikes Back (1980)"))
        MatcherAssert.assertThat(characterFilteres.value[2].charactter.id, `is`(5))
        MatcherAssert.assertThat(characterFilteres.value[2].charactter.name, `is`("Leia Organa"))

        MatcherAssert.assertThat(characterFilteres.value[3].foundFilm, `is`("Star Wars: Episode V - The Empire Strikes Back (1980)"))
        MatcherAssert.assertThat(characterFilteres.value[3].charactter.id, `is`(6))
        MatcherAssert.assertThat(characterFilteres.value[3].charactter.name, `is`("Yoda"))

        MatcherAssert.assertThat(characterFilteres.value[4].foundFilm, `is`("Star Wars: Episode V - The Empire Strikes Back (1980)"))
        MatcherAssert.assertThat(characterFilteres.value[4].charactter.id, `is`(20))
        MatcherAssert.assertThat(characterFilteres.value[4].charactter.name, `is`("Boba Fett"))

        verify(charactterDao).getCharacttersWithNameInAdapterFormat(query)
        verify(filmDao).getFilmsFilteresbyName(query)



    }

    @Test
    fun loadAllFilms(){

        val allFilms = getFilmResponse().items

        `when`(filmDao.getAllFilms()).thenReturn(allFilms)
        val filmLiveData = repository.loadAllFilms()

        val observer = mock<Observer<List<Film>>>()
        filmLiveData.observeForever(observer)
        verifyNoMoreInteractions(starWarsApi)
        verifyNoMoreInteractions(charactterDao)

        verify(observer).onChanged(allFilms)
    }

    @Test
    fun findFilmThatHasCharacterWithNoFilm(){

        val characterid = 99

        val allFilms = getFilmResponse().items

        `when`(filmDao.getAllFilms()).thenReturn(allFilms)
        val data = repository.findFilmThatHasCharacter(characterid)

        val observer = mock<Observer<String>>()
        data.observeForever(observer)
        val filmsThatHasCharacter = argumentCaptor<String>()
        verify(observer).onChanged(filmsThatHasCharacter.capture())
        MatcherAssert.assertThat(filmsThatHasCharacter.value, `is`(""))
    }

    @Test
    fun findFilmThatHasCharacter(){

        val characterid = 0

        val allFilms = getFilmResponse().items

        `when`(filmDao.getAllFilms()).thenReturn(allFilms)
        val data = repository.findFilmThatHasCharacter(characterid)

        val observer = mock<Observer<String>>()
        data.observeForever(observer)
        val filmsThatHasCharacter = argumentCaptor<String>()
        verify(observer).onChanged(filmsThatHasCharacter.capture())
        MatcherAssert.assertThat(filmsThatHasCharacter.value, `is`("Star Wars: Episode IV - A New Hope (1977), Star Wars: Episode V - The Empire Strikes Back (1980)"))
    }

    @Test
    fun findCharactersInFilm(){

        val listOfCharactersIds = listOf(0)
        val listOfCharacters = listOf(getCharacterResponse().items[0]).map { CharacterAdapterPojo(it,null) }

        `when`(charactterDao.getCharacttersByIdsWithAdapterFormat(listOfCharactersIds)).thenReturn(listOfCharacters)
        val data = repository.findCharactersInFilm(listOfCharactersIds)
        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        data.observeForever(observer)
        verify(observer).onChanged(listOfCharacters)
    }




}