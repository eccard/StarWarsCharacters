package com.eccard.starwarscharacters.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
public open class Charactter(
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
) : RealmObject(), Parcelable

