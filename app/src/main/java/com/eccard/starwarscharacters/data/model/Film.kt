package com.eccard.starwarscharacters.data.model

import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.eccard.starwarscharacters.util.IntListTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"]
)
data class Film (
    @SerializedName("id")
    val id : Int = 0,

    @field:SerializedName("name")
    val name : String,

    @field:SerializedName("complete_name")
    val completeName : String,

    @field:SerializedName("launch_date")
    val launchDate : String,


    @field:SerializedName("character_ids")
    val charactersIds : List<Int>?,

    @field:SerializedName("synopsis")
    val synopsis : String,


    @field:SerializedName("director")
    val director : String,

    @field:SerializedName("trailer")
    val trailer : String,

    @field:SerializedName("cover_album")
    val coverAlbum : String

)