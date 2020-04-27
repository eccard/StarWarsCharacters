package com.eccard.starwarscharacters.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.api.CharactterResponse
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.InstantAppExecutors
import com.eccard.starwarscharacters.util.argumentCaptor
import com.eccard.starwarscharacters.util.mock
import com.google.gson.Gson
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


    private fun getCharacterThatIsInFilm() : List<Charactter> {
        return Gson().fromJson<CharactterResponse>(
                """
                {
                    "total_count": 3,
                    "items": [{
                            "id": 5,
                            "isMain": true,
                            "name": "Leia Organa",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg",
                            "gender": "female"
                        },
                        {
                            "id": 6,
                            "isMain": true,
                            "name": "Yoda",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Yoda_Empire_Strikes_Back.png",
                            "gender": "male"
                        },
                        {
                            "id": 20,
                            "isMain": true,
                            "name": "Boba Fett",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png",
                            "gender": "male"
                        }
                    ]
                }
                """
            , CharactterResponse::class.java).items
    }

    private fun getCharacterResponse() : CharactterResponse{
        val listOfCharacers = mutableListOf<Charactter>()
        listOfCharacers.add(
            Charactter(0,
                true,
                "Anakin Skywalker, Darth Vader",
                "https://66.media.tumblr.com/9856bd42f18ce548f05d1c2701d298be/tumblr_pxqwf3OeH81w4t7wqo1_500.jpg",
                "male")
        )
        listOfCharacers.add(
            Charactter(2,
                true,
                "Ben Solo, Kylo Ren",
                "https://i.pinimg.com/236x/71/06/dc/7106dc5edb54d56cb15492e1772fbec5.jpg",
                "male")
        )
        return CharactterResponse(2,listOfCharacers)
    }

    private fun getFilmResponse() : FilmRespose {
        val listOfFilm = mutableListOf<Film>()
        val gson = Gson()
        listOfFilm.add(gson.fromJson<Film>("{" +
                "      \"id\": 0," +
                "      \"name\" : \"A New Hope\"," +
                "      \"complete_name\": \"Star Wars: Episode IV - A New Hope (1977)\"," +
                "      \"launch_date\": \"1977-05-25\"," +
                "      \"character_ids\": [4,10,5,55,3,8,7,11,0]," +
                "      \"synopsis\" : \"Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.\"," +
                "      \"director\" : \"George Lucas\"," +
                "      \"cover_album\" : \"https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,643,1000_AL_.jpg\"," +
                "      \"trailer\" : \"https://www.youtube.com/watch?v=1g3_CFmnU7k\"" +
                "    }",Film::class.java)
        )

        listOfFilm.add(gson.fromJson<Film>("{" +
                    "      \"id\": 1," +
                    "      \"name\" : \"The Empire Strikes Back\"," +
                    "      \"complete_name\": \"Star Wars: Episode V - The Empire Strikes Back (1980)\"," +
                    "      \"launch_date\": \"1980-05-21\"," +
                    "      \"character_ids\": [4,10,5,8,0,11,7,6,3,20,63]," +
                    "      \"synopsis\" : \"After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.\"," +
                    "      \"director\": \"Irvin Kershner\"," +
                    "      \"cover_album\" : \"https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,641,1000_AL_.jpg\"," +
                    "      \"trailer\" : \"https://www.youtube.com/watch?v=JNwNXF9Y6kY\"" +
                    "    }",Film::class.java)
        )
        return FilmRespose(2,listOfFilm)
    }
}