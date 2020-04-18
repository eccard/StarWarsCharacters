package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {

    @GET(Constants.CHARACTER)
    fun getCaracters() : Call<CharactterResponse>

    @GET(Constants.FILMS)
    fun getFilms() : Call<FilmRespose>
}