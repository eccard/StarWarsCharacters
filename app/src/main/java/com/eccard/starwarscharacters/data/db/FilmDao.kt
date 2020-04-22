package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Film
import io.realm.Realm
import timber.log.Timber
import java.lang.Exception

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
                .findAll().map {it.clone()}
        } catch (exeption : Exception){
                Timber.e(exeption)
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
                .contains("completeName",name)
                .findAll().map { it.clone() }
        } catch (exeption : Exception){
            Timber.e(exeption)
        }finally {
            realm?.close()
        }

        return filmList ?: emptyList()
    }

}