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
    var gender : String = "",
    @field:SerializedName("first_appearance")
    var firstAppearance : String? = null,
    @field:SerializedName("last_appearance")
    var lastAppearance : String? = null
) : RealmModel, Parcelable {
    fun clone() : Charactter{
        return Charactter(this.id,this.isMain,this.name,this.imageUrl,this.gender,this.firstAppearance,this.lastAppearance)
    }
}



