package com.eccard.starwarscharacters.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"]
)
data class Charactter(
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("isMain")
    val isMain : Boolean,
    @field:SerializedName("name")
    val name : String,
    @field:SerializedName("image_url")
    val imageUrl : String
)

