package com.eccard.starwarscharacters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.IntListTypeConverter

@Database(
    entities = [
        Charactter::class,
        Film::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntListTypeConverter::class)
abstract class StarWarsDb : RoomDatabase() {

    abstract fun characterDao() : CharactterDao
    abstract fun filmDao() : FilmDao
}