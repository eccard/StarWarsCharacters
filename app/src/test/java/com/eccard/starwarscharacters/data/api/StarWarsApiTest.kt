package com.eccard.starwarscharacters.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eccard.starwarscharacters.data.model.Charactter
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

        checkCharacter(characterAnakin,
            0,
            "Anakin Skywalker, Darth Vader",
            "male",
            true,
            "https://66.media.tumblr.com/9856bd42f18ce548f05d1c2701d298be/tumblr_pxqwf3OeH81w4t7wqo1_500.jpg"
        )

        val characterKylo = call.body()!!.items[1]
        checkCharacter(characterKylo,
            2,
            "Ben Solo, Kylo Ren",
            "male",
            true,
            "https://i.pinimg.com/236x/71/06/dc/7106dc5edb54d56cb15492e1772fbec5.jpg"
        )

        val characterKenobi = call.body()!!.items[2]
        checkCharacter(characterKenobi,
            3,
            "Obi-Wan Kenobi",
            "male",
            true,
            "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816"
            )

        val characterLeia = call.body()!!.items[3]
        checkCharacter(characterLeia,
            5,
            "Leia Organa",
            "female",
            true,
            "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg"
        )

        val characterAntilles = call.body()!!.items[4]
        checkCharacter(characterAntilles,
            35,
            "Antilles",
            "",
            false,
            ""
        )

    }

    private fun checkCharacter(character : Charactter,
                               expectedId : Int,
                               expectedName : String,
                               expectedGender : String,
                               expectedIsMain : Boolean,
                               expectedImageUrl : String){
        Assert.assertThat(character.id,`is`(expectedId))
        Assert.assertThat(character.name, `is`(expectedName))
        Assert.assertThat(character.gender, `is`(expectedGender))
        Assert.assertThat(character.isMain, `is`(expectedIsMain))
        Assert.assertThat(character.imageUrl, `is`(expectedImageUrl))

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
