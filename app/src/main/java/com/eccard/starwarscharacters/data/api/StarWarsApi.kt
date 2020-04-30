package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {

    @GET(Constants.API_CHARACTER)
    fun getCaracters() : Call<CharactterResponse>

    @GET(Constants.API_FILMS)
    fun getFilms() : Call<FilmRespose>
}