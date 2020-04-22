package com.eccard.starwarscharacters.data.model

import android.os.Parcelable
import com.eccard.starwarscharacters.util.FilmRealmListParceler
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.WriteWith


@Parcelize
@RealmClass
open class Film (
    @SerializedName("id")
    @PrimaryKey
    var id : Int = 0,

    @field:SerializedName("name")
    var name : String = "",

    @field:SerializedName("complete_name")
    var completeName : String = "",

    @field:SerializedName("launch_date")
    var launchDate : String = "",


    @field:SerializedName("character_ids")
    var charactersIds :
    @WriteWith<FilmRealmListParceler> RealmList<Film> = RealmList(),

    @field:SerializedName("synopsis")
    var synopsis : String = "",


    @field:SerializedName("director")
    var director : String = "",

    @field:SerializedName("trailer")
    var trailer : String = "",

    @field:SerializedName("cover_album")
    var coverAlbum : String = ""

) : RealmModel, Parcelable