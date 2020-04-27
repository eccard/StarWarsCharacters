package com.eccard.starwarscharacters.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@RealmClass
open class Charactter(
    @field:SerializedName("id")
    @PrimaryKey
    var id : Int = 0,
    @field:SerializedName("isMain")
    var isMain : Boolean = false,
    @field:SerializedName("name")
    var name : String = "",
    @field:SerializedName("image_url")
    var imageUrl : String = "",
    @field:SerializedName("gender")
    var gender : String = ""
) : RealmModel, Parcelable



