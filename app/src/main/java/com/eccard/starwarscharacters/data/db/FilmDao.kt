package com.eccard.starwarscharacters.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eccard.starwarscharacters.data.model.Film

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fiml: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fimlList: List<Film>)

    @Query("SELECT * FROM  film")
    fun getAllFilms() : List<Film>

    @Query("SELECT * FROM film WHERE completeName LIKE '%' || :name || '%'")
    fun getFilmsFilteresbyName(name : String) : List<Film>

}