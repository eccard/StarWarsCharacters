package com.eccard.starwarscharacters.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eccard.starwarscharacters.data.model.Charactter

@Dao
interface CharactterDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(charactter: Charactter)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(charactterList: List<Charactter>)

    @Query ("SELECT * FROM  charactter")
    fun getAllCharactters() : List<Charactter>

    @Query("SELECT * FROM charactter WHERE name LIKE '%' || :name || '%'")
    fun getCharacttersWithName(name : String) : List<Charactter>

    @Query("SELECT * FROM charactter WHERE id IN (:ids)")
    fun getCharacttersByIds(ids : List<Int>) : List<Charactter>
}