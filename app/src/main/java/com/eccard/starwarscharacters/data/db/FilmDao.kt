package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Film
import io.realm.Realm

//class FilmDao(private val realmx: Realm) {
class FilmDao() {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fiml: Film){
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        realm.copyToRealmOrUpdate(fiml)
        realm.commitTransaction()
    }

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fimlList: List<Film>){
        val realm = Realm.getDefaultInstance()
        val v1 = realm.beginTransaction()
        val v2 = realm.copyToRealmOrUpdate(fimlList)
        val v3 = realm.commitTransaction()
    }

//    @Query("SELECT * FROM  film")
    fun getAllFilms() : List<Film>{
        val realm = Realm.getDefaultInstance()
        val v1 = realm.where(Film::class.java)
            .findAll()
        return v1
    }

//    @Query("SELECT * FROM film WHERE completeName LIKE '%' || :name || '%'")
    fun getFilmsFilteresbyName(name : String) : List<Film>{
        val realm = Realm.getDefaultInstance()
        val v1 = realm.where(Film::class.java)
            .contains("completeName",name)
            .findAll()
        return v1
    }

}