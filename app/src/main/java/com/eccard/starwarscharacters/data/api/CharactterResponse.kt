package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.data.model.Charactter
import com.google.gson.annotations.SerializedName

data class CharactterResponse(
    @SerializedName("total_count")
    val total : Int = 0,

    @SerializedName("items")
    val items : List<Charactter>
)