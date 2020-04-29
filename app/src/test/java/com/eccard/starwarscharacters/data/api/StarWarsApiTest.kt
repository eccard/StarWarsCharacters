package com.eccard.starwarscharacters.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.Constants
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class StarWarsApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var starWarsApi: StarWarsApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        starWarsApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getCaracters(){
        enqueueResponse("characters_response.json")
        val call = starWarsApi.getCaracters().execute()

        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/"+Constants.CHARACTER))

        Assert.assertThat<CharactterResponse>(call.body(), IsNull.notNullValue())
        Assert.assertThat(call.body()!!.total, `is`(5))

        val characterAnakin = call.body()!!.items[0]
        characterAnakin.checkCharacter(
            0,
            "Anakin Skywalker, Darth Vader",
            "male",
            true,
            "https://66.media.tumblr.com/9856bd42f18ce548f05d1c2701d298be/tumblr_pxqwf3OeH81w4t7wqo1_500.jpg"
        )

        val characterKylo = call.body()!!.items[1]
        characterKylo.checkCharacter(
            2,
            "Ben Solo, Kylo Ren",
            "male",
            true,
            "https://i.pinimg.com/236x/71/06/dc/7106dc5edb54d56cb15492e1772fbec5.jpg"
        )

        val characterKenobi = call.body()!!.items[2]
        characterKenobi.checkCharacter(
            3,
            "Obi-Wan Kenobi",
            "male",
            true,
            "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816"
            )

        val characterLeia = call.body()!!.items[3]
        characterLeia.checkCharacter(
            5,
            "Leia Organa",
            "female",
            true,
            "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg"
        )

        val characterAntilles = call.body()!!.items[4]
        characterAntilles.checkCharacter(
            35,
            "Antilles",
            "",
            false,
            ""
        )

    }

    @Test
    fun getFilms(){
        enqueueResponse("films_response.json")
        val call = starWarsApi.getFilms().execute()
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/"+Constants.FILMS))

        Assert.assertThat<FilmRespose>(call.body(), IsNull.notNullValue())
        Assert.assertThat(call.body()!!.total, `is`(3))

        val film1977 = call.body()!!.items[0]
        film1977.checkInfos(
            0,
            "A New Hope",
            "Star Wars: Episode IV - A New Hope (1977)",
            "1977-05-25",
            arrayListOf(4,10,5,55,3,8,7,11,0),
            "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
            "George Lucas",
            "https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,643,1000_AL_.jpg",
            "https://www.youtube.com/watch?v=1g3_CFmnU7k"
        )

        val film1980 = call.body()!!.items[1]
        film1980.checkInfos(
            1,
            "The Empire Strikes Back",
            "Star Wars: Episode V - The Empire Strikes Back (1980)",
            "1980-05-21",
            arrayListOf(4,10,5,8,0,11,7,6,3,20,63),
            "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
            "Irvin Kershner",
            "https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,641,1000_AL_.jpg",
            "https://www.youtube.com/watch?v=JNwNXF9Y6kY"
        )

        val film1983 = call.body()!!.items[2]
        film1983.checkInfos(
            2,
            "Return of the Jedi",
            "Star Wars: Episode VI - Return of the Jedi (1983)",
            "1983-05-25",
            arrayListOf(4,10,5,18,8,11,0,6,3,7,38,65,90,20),
            "After a daring mission to rescue Han Solo from Jabba the Hutt, the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, Luke struggles to help Darth Vader back from the dark side without falling into the Emperor's trap.",
            "Richard Marquand",
            "https://m.media-amazon.com/images/M/MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY999_CR0,0,644,999_AL_.jpg",
            "https://www.youtube.com/watch?v=5UfA_aKBGMc"
        )

    }

    private fun Film.checkInfos(expectedId : Int,
                        expectedName : String,
                        expectedCompleteName : String,
                        expectedLaunchDate : String,
                        expectedCharsIds : List<Int>,
                        expectedSynopsis : String,
                        expectedDirector : String,
                        expectedCoverAlbum : String,
                        expectedTrailer : String) {
        Assert.assertThat(this.id,`is`(expectedId))
        Assert.assertThat(this.name, `is`(expectedName))
        Assert.assertThat(this.completeName, `is`(expectedCompleteName))
        Assert.assertThat(this.launchDate, `is`(expectedLaunchDate))
        Assert.assertThat(this.synopsis, `is`(expectedSynopsis))
        Assert.assertThat(this.director, `is`(expectedDirector))
        Assert.assertThat(this.coverAlbum, `is`(expectedCoverAlbum))
        Assert.assertThat(this.trailer, `is`(expectedTrailer))

        Assert.assertThat(this.charactersIds.size,`is`(expectedCharsIds.size))

        for (realmInt in this.charactersIds){
            Assert.assertThat(expectedCharsIds.contains(realmInt._value),`is`(true))
        }
    }

    private fun Charactter.checkCharacter(expectedId : Int,
                            expectedName : String,
                            expectedGender : String,
                            expectedIsMain : Boolean,
                            expectedImageUrl : String) {
        Assert.assertThat(this.id,`is`(expectedId))
        Assert.assertThat(this.name, `is`(expectedName))
        Assert.assertThat(this.gender, `is`(expectedGender))
        Assert.assertThat(this.isMain, `is`(expectedIsMain))
        Assert.assertThat(this.imageUrl, `is`(expectedImageUrl))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )

    }

}
