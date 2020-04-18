package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.data.model.Film
import com.google.gson.annotations.SerializedName

data class FilmRespose (
    @SerializedName("total_count")
    val total : Int = 0,

    @SerializedName("items")
    val items : List<Film>
)