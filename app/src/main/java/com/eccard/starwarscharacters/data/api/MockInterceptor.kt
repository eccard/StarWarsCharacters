package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.BuildConfig
import com.eccard.starwarscharacters.data.api.CharacterResponseMock.Companion.getCharacters
import com.eccard.starwarscharacters.data.api.FilmResponseMock.Companion.getFilms
import com.eccard.starwarscharacters.util.Constants
import okhttp3.*
import timber.log.Timber

class MockInterceptor : Interceptor {
    companion object {
        const val simulateNetTime = 300
    }
    override fun intercept(chain: Interceptor.Chain): Response {
//        as the api isn't workin, this will for debug and Release
        val uri = chain.request().url().uri().toString()
        Timber.d("intercept $uri")

        val responseString = when (uri) {
            BuildConfig.BASE_URL + "/" + Constants.API_CHARACTER -> {
                getCharacters()
            }
            BuildConfig.BASE_URL + "/" + Constants.API_FILMS -> {
                getFilms()
            }
            else -> {
                ""
            }
        }

	Timber.d("simulating internet delay init")
        Thread.sleep(simulateNetTime.toLong())
	Timber.d("simulating internet delay end")

        return Response.Builder()
            .request(chain.request())
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                MediaType.parse("application/json"),
                responseString.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()

    }
}


