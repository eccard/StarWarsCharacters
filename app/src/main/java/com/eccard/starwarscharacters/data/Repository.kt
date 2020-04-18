package com.eccard.starwarscharacters.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.data.api.CharactterResponse
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.model.Charactter
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

    private val result = MediatorLiveData<List<Charactter>>()

    fun asLiveData() = result as LiveData<List<Charactter>>

    fun loadCharactersFromApi(){
       starWarsApi.getCaracters().enqueue(object : Callback<CharactterResponse?> {
            override fun onFailure(call: Call<CharactterResponse?>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(
                call: Call<CharactterResponse?>,
                response: Response<CharactterResponse?>
            ) {
                response.body()?.let {

                    appExecutors.diskIO().execute {
                        charactterDao.insert(it.items)
                        result.postValue(charactterDao.getAllCharactters())
                    }


                    Timber.d("onResponse ${it.toString()}")
//                    result.value = it.items

                    // todo save in db
                }
            }
        })

        loadFilmlFromApi()
    }

    fun loadFilmlFromApi(){
        starWarsApi.getFilms().enqueue(object : Callback<FilmRespose?> {
            override fun onFailure(call: Call<FilmRespose?>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<FilmRespose?>, response: Response<FilmRespose?>) {
                response.body()?.let {
                    appExecutors.diskIO().execute {
                        filmDao.insert(it.items)
                    }
                }
            }
        })
    }

    fun loadAllCharacttersFromDb(){
        appExecutors.diskIO().execute{
            result.postValue(charactterDao.getAllCharactters())
        }
    }

    fun findByNameOrFilm(query : String) : LiveData<List<Charactter>>{

        appExecutors.diskIO().execute {
            if ( query.isBlank()){
                result.postValue(charactterDao.getAllCharactters())
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

                val idList = charactesrInFilms.toList()

                val charactersInFilms = charactterDao.getCharacttersByIds(idList)

                val mutableList = mutableListOf<Charactter>()
                mutableList.addAll(characttersFilteredByName)
                mutableList.addAll(charactersInFilms)

                result.postValue(mutableList)
            }
        }

        return asLiveData()
    }

}