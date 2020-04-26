package com.eccard.starwarscharacters.data

import android.content.Context
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.data.api.CharactterResponse
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val starWarsApi: StarWarsApi,
    private val charactterDao: CharactterDao,
    private val filmDao : FilmDao,
    private val context : Context) {

    private val charactersResult = MediatorLiveData<List<CharacterAdapterPojo>>()
    private val filmResult = MediatorLiveData<List<Film>>()

    private fun charactersAsLiveData() = charactersResult as LiveData<List<CharacterAdapterPojo>>
    private fun filmAsLiveData() = filmResult as LiveData<List<Film>>

    var syncListener : SyncListener? = null


    interface SyncListener{
        fun onSynced()
    }


    fun syncWithApi(){
       starWarsApi.getCaracters().enqueue(object : Callback<CharactterResponse?> {
            override fun onFailure(call: Call<CharactterResponse?>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(
                call: Call<CharactterResponse?>,
                response: Response<CharactterResponse?>
            ) {
                response.body()?.let {
                    loadFilmlFromApi()
                    appExecutors.diskIO().execute {
                        charactterDao.insert(it.items)
                        charactersResult.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
                    }

                }
            }
        })

    }

    private fun loadFilmlFromApi(){
        starWarsApi.getFilms().enqueue(object : Callback<FilmRespose?> {
            override fun onFailure(call: Call<FilmRespose?>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<FilmRespose?>, response: Response<FilmRespose?>) {
                response.body()?.let {
                    syncListener?.onSynced()
                    appExecutors.diskIO().execute {
                        filmDao.insert(it.items)
                    }
                }
            }
        })
    }

    fun loadAllCharacttersFromDb(){
        appExecutors.diskIO().execute{
            charactersResult.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
        }
    }

    fun findByNameOrFilm(query : String) : LiveData<List<CharacterAdapterPojo>>{
        if ( query.isBlank()){
            charactersResult.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
        } else {

            val result = Realm.getDefaultInstance().where(Charactter::class.java).contains("name","ttt")
                .findAllAsync()


        appExecutors.diskIO().execute {

                val characttersFilteredByName = charactterDao.getCharacttersWithName(query)

                val films = filmDao.getFilmsFilteresbyName(query)

                val charactesrInFilms = HashSet<Int>()


                for (film in films){
                    for (characterId in film.charactersIds){
                        charactesrInFilms.add(characterId._value)
                    }
                }

                // removing form query all characteres filteres by name
                for(character in characttersFilteredByName){
                    charactesrInFilms.remove(character.id)
                }

                val charactersInFilms = charactterDao.getCharacttersByIds(charactesrInFilms.toList())


                val mutableListAdapter = characttersFilteredByName.map { CharacterAdapterPojo(it,null) }

                val characterInFilmsPojo = search(films,charactersInFilms)

                val mutableList = mutableListOf<CharacterAdapterPojo>()
                mutableList.addAll(mutableListAdapter)
                mutableList.addAll(characterInFilmsPojo)

                charactersResult.postValue(mutableList)
            }
        }

        return charactersAsLiveData()
    }

    private fun search(films : List<Film>, charactersInFilms  : List<Charactter>) : List<CharacterAdapterPojo> {
        val list = mutableListOf<CharacterAdapterPojo>()
        for ( chars in charactersInFilms){
            list.add(CharacterAdapterPojo(chars, checkIfCharacterInFilmAndReturnFilmName(films,chars.id)))
        }
        return list
    }

    private fun checkIfCharacterInFilmAndReturnFilmName(films : List<Film>, characterId : Int) : String {
        val sBuffer = StringBuffer()
        for (film in films){
            for (charactersInFilm in film.charactersIds){
                if (charactersInFilm._value == characterId){
                    sBuffer.append(film.completeName)
                    sBuffer.append(", ")
                }
            }
        }
        if (sBuffer.isNotEmpty()){
            sBuffer.setLength(sBuffer.length -2)
        }
        return sBuffer.toString()
    }


    fun loadAllFilms() : LiveData<List<Film>> {
        appExecutors.diskIO().execute {
            filmResult.postValue(filmDao.getAllFilms())

        }

        return filmAsLiveData()
    }

    private val nameOfFilms = MediatorLiveData<String>()
    fun findFilmThatHasCharacter(characterId : Int) : LiveData<String>{
        appExecutors.diskIO().execute {
            nameOfFilms.postValue(
                checkIfCharacterInFilmAndReturnFilmName(filmDao.getAllFilms(),characterId)
            )
        }
        return nameOfFilms
    }

    private val charactersInFilm = MediatorLiveData<List<CharacterAdapterPojo>>()
    fun findCharactersInFilm(charactersIds : List<Int>) : LiveData<List<CharacterAdapterPojo>>{
            appExecutors.diskIO().execute {
                charactersInFilm.postValue(
                    charactterDao.getCharacttersByIds(charactersIds).map { CharacterAdapterPojo(it,null) }
                )
            }
        return charactersInFilm
    }

    private val videoUrl = MediatorLiveData<String>()
    fun resolveVideoLink(url : String) : LiveData<String>{
        object : YouTubeExtractor(context) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                ytFiles?.let {
                    val itag = 18
                    videoUrl.postValue(ytFiles[itag].url)
                }
                var abc = 1
                abc++
            }

        }.extract(url, true, false)
        return videoUrl
    }


}
