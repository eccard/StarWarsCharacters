package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.testing.OpenForTesting
import com.eccard.starwarscharacters.util.realm.Util
import io.realm.Case
import io.realm.Realm
import timber.log.Timber

@OpenForTesting
class FilmDao {

    fun insert(filmList: List<Film>){
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            realm.executeTransaction {
                it.copyToRealmOrUpdate(filmList)
            }
        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }
    }

    fun getAllFilms() : List<Film>{
        var filmList : List<Film>? = null
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            filmList = realm.copyFromRealm(realm.where(Film::class.java)
                    .findAll())
        } catch (exception : Exception){
                Timber.e(exception)
        }finally {
            realm?.close()
        }

        return filmList ?: emptyList()
    }

    fun getFilmsFilteredByName(name : String) : List<Film>{

        var filmList : List<Film>? = null
        var realm : Realm? = null

        try {
            realm = Util.getRealm()
            filmList = realm.copyFromRealm(realm.where(Film::class.java)
                .contains("completeName",name, Case.INSENSITIVE)
                .findAll())
        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }

        return filmList ?: emptyList()
    }

}

