package com.eccard.starwarscharacters.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.data.api.CharactterResponse
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
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
    private val filmDao : FilmDao) {

    private val result = MediatorLiveData<List<CharacterAdapterPojo>>()
    private val isSyncyngWithAPI = MediatorLiveData<Boolean>()

    fun asLiveData() = result as LiveData<List<CharacterAdapterPojo>>

    fun syncWithApi() : LiveData<Boolean> {
       isSyncyngWithAPI.postValue(true)
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
                        result.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
                    }

                }
            }
        })

        return isSyncyngWithAPI
    }

    private fun loadFilmlFromApi(){
        starWarsApi.getFilms().enqueue(object : Callback<FilmRespose?> {
            override fun onFailure(call: Call<FilmRespose?>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<FilmRespose?>, response: Response<FilmRespose?>) {
                response.body()?.let {
                    isSyncyngWithAPI.postValue(false)
                    appExecutors.diskIO().execute {
                        filmDao.insert(it.items)
                    }
                }
            }
        })
    }

    fun loadAllCharacttersFromDb(){
        appExecutors.diskIO().execute{
            result.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
        }
    }

    fun findByNameOrFilm(query : String) : LiveData<List<CharacterAdapterPojo>>{

        appExecutors.diskIO().execute {
            if ( query.isBlank()){
                result.postValue(charactterDao.getAllCharactters().map { CharacterAdapterPojo(it,null) })
            } else {
                val characttersFilteredByName = charactterDao.getCharacttersWithName(query)

                val films = filmDao.getFilmsFilteresbyName(query)

                val charactesrInFilms = HashSet<Int>()


                for (film in films){
                    film.charactersIds?.let {
                        charactesrInFilms.addAll(it)
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

                result.postValue(mutableList)
            }
        }

        return asLiveData()
    }

    private fun search(films : List<Film>, charactersInFilms  : List<Charactter>) : List<CharacterAdapterPojo> {
        val list = mutableListOf<CharacterAdapterPojo>()

        for ( chars in charactersInFilms){
            val sBuffer = StringBuffer()

            for (film in films){
                film.charactersIds?.let {
                        if (it.contains(chars.id)){
                            sBuffer.append(film.name)
                            sBuffer.append(", ")
                    }
                }
            }
            list.add(CharacterAdapterPojo(chars, sBuffer.toString()))
        }

        return list
    }

}
