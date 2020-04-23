package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.data.model.RealmInt
import io.realm.Case
import io.realm.Realm
import io.realm.RealmList
import timber.log.Timber

class FilmDao {

    fun insert(fimlList: List<Film>){
        var realm : Realm? = null
        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                it.copyToRealmOrUpdate(fimlList)
            }
        } catch (exeption : Exception){
            Timber.e(exeption)
        }finally {
            realm?.close()
        }
    }

    fun getAllFilms() : List<Film>{
        var filmList : List<Film>? = null
        var realm : Realm? = null
        try {
            realm = Realm.getDefaultInstance()
            filmList = realm.where(Film::class.java)
                .findAll().map {it.copyFromRealm()}
        } catch (exception : Exception){
                Timber.e(exception)
        }finally {
            realm?.close()
        }

        return filmList ?: emptyList()
    }

    fun getFilmsFilteresbyName(name : String) : List<Film>{

        var filmList : List<Film>? = null
        var realm : Realm? = null

        try {
            realm = Realm.getDefaultInstance()
            filmList = realm.where(Film::class.java)
                .contains("completeName",name, Case.INSENSITIVE)
                .findAll().map { it.copyFromRealm() }
        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }

        return filmList ?: emptyList()
    }

    private fun Film.copyFromRealm(): Film {
        val list = RealmList<RealmInt>()
        for (char in this.charactersIds){
            list.add(RealmInt(char._value))
        }
        return Film(this.id,this.name,this.completeName,this.launchDate,list,this.synopsis,this.director,this.trailer,this.coverAlbum)
    }



}

