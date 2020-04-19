package com.eccard.starwarscharacters.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
    primaryKeys = ["id"]
)
@Parcelize
data class Charactter(
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("isMain")
    val isMain : Boolean,
    @field:SerializedName("name")
    val name : String,
    @field:SerializedName("image_url")
    val imageUrl : String,
    @field:SerializedName("gender")
    val gender : String,
    @field:SerializedName("first_appearance")
    val firstAppearance : String?,
    @field:SerializedName("last_appearance")
    val lastAppearance : String?
) : Parcelable

