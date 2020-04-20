package com.eccard.starwarscharacters.data.api

import com.eccard.starwarscharacters.BuildConfig
import com.eccard.starwarscharacters.util.Constants
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.MediaType
import timber.log.Timber

class MockInterceptor : Interceptor {
    companion object {
        const val simulateNetTime = 300
    }
    override fun intercept(chain: Interceptor.Chain): Response {
//        as the api isn't workin, this will for debug and Release
        val uri = chain.request().url().uri().toString()
        Timber.d("intercept $uri")

        val responseString = if (uri == BuildConfig.BASE_URL + "/" + Constants.CHARACTER){
            getCharacters
        } else if (uri == BuildConfig.BASE_URL + "/" + Constants.FILMS) {
            getFilms
        } else {
            ""
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

const val getCharacters = """ 
{
 	"total_count": 95,
 	"items": [{
 			"id": 0,
 			"isMain": true,
 			"name": "Anakin Skywalker/Darth Vader",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/7/76/Darth_Vader.jpg",
 			"gender": "male",
 			"first_appearance": "Star Wars (film)"
 		},
 		{
 			"id": 2,
 			"isMain": true,
 			"name": "Ben Solo/Kylo Ren",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/3/34/Kylo_Ren.png/330px-Kylo_Ren.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: The Force Awakens",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 3,
 			"isMain": true,
 			"name": "Obi-Wan Kenobi",
 			"image_url": "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816",
 			"gender": "male",
 			"first_appearance": "Star Wars (film)"
 		},
 		{
 			"id": 4,
 			"isMain": true,
 			"name": "Luke Skywalker",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png",
 			"gender": "male",
 			"first_appearance": "Star Wars (film)",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 5,
 			"isMain": true,
 			"name": "Leia Organa",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg",
 			"gender": "female",
 			"first_appearance": "Star Wars (film)",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 6,
 			"isMain": true,
 			"name": "Yoda",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Yoda_Empire_Strikes_Back.png",
 			"gender": "male",
 			"first_appearance": "The Empire Strikes Back",
 			"last_appearance": ""
 		},
 		{
 			"id": 7,
 			"isMain": true,
 			"name": "R2-D2",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/R2-D2_Droid.png",
 			"gender": "",
 			"first_appearance": "Star Wars (film)",
 			"last_appearance": ""
 		},
 		{
 			"id": 8,
 			"isMain": true,
 			"name": "C-3PO",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/5/5c/C-3PO_droid.png",
 			"gender": "",
 			"first_appearance": "Star Wars (film)"
 		},
 		{
 			"id": 9,
 			"isMain": true,
 			"name": "BB-8",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/BB-8%2C_Star_Wars_The_Force_Awakens.jpg",
 			"gender": "",
 			"first_appearance": "Star Wars: The Force Awakens"
 		},
 		{
 			"id": 10,
 			"isMain": true,
 			"name": "Han Solo",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg",
 			"gender": "male",
 			"first_appearance": "Star Wars (film)",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 11,
 			"isMain": true,
 			"name": "Chewbacca",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/6/6d/Chewbacca-2-.jpg",
 			"gender": "male",
 			"first_appearance": "Star Wars (film)"
 		},
 		{
 			"id": 12,
 			"isMain": true,
 			"name": "Padmé Amidala",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/ee/Amidala.png/220px-Amidala.png",
 			"gender": "female",
 			"first_appearance": "Star Wars: Episode I – The Phantom Menace"
 		},
 		{
 			"id": 13,
 			"isMain": true,
 			"name": "Mace Windu",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Mace_Windu.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: Episode I – The Phantom Menace",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 14,
 			"isMain": true,
 			"name": "Qui-Gon Jinn",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/a/ad/Qui-Gon_Jinn.png/220px-Qui-Gon_Jinn.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: Episode I – The Phantom Menace",
 			"last_appearance": "Star Wars: The Rise of Skywalker"

 		},
 		{
 			"id": 15,
 			"isMain": true,
 			"name": "Vice Almirante Holdo",
 			"image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/a/a4/Holdo-Elle.png/revision/latest?cb=20171214221607",
 			"gender": ""
 		},
 		{
 			"id": 16,
 			"isMain": true,
 			"name": "Rose Tico",
 			"image_url": "https://pm1.narvii.com/6521/766f393132d82c1087e11e1e505ccd464b5188da_128.jpg",
 			"gender": "female",
 			"first_appearance": "Star Wars: The Last Jedi"
 		},
 		{
 			"id": 17,
 			"isMain": true,
 			"name": "Poe Dameron",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/0/0b/Poe_Dameron-Force_Awakens_%282015%29.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: The Force Awakens"
 		},
 		{
 			"id": 18,
 			"isMain": true,
 			"name": "Lando Calrissian",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Lando6-2.jpg/330px-Lando6-2.jpg",
 			"gender": "male",
 			"first_appearance": "The Empire Strikes Back"
 		},
 		{
 			"id": 19,
 			"isMain": true,
 			"name": "Darth Maul",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Darth_Maul.png",
 			"gender": ""
 		},
 		{
 			"id": 20,
 			"isMain": true,
 			"name": "Boba Fett",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png",
 			"gender": "male",
 			"first_appearance": "Star Wars Holiday Special"
 		},
 		{
 			"id": 21,
 			"isMain": true,
 			"name": "Jabba, the Hutt",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png/330px-Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png",
 			"gender": "male",
 			"first_appearance": "Star Wars",
 			"last_appearance": "The Clone Wars"
 		},
 		{
 			"id": 22,
 			"isMain": true,
 			"name": "Jango Fett",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Jango_Fett.png/330px-Jango_Fett.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: Episode II – Attack of the Clones",
 			"last_appearance": "The Clone Wars"
 		},
 		{
 			"id": 23,
 			"isMain": true,
 			"name": "Conde Dooku/Darth Tyranus",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/b/bd/Count_Dooku.png/330px-Count_Dooku.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: Episode II – Attack of the Clones"
 		},
 		{
 			"id": 24,
 			"isMain": true,
 			"name": "General Grievous",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/GeneralGrievous.png/220px-GeneralGrievous.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: Clone Wars ",
 			"last_appearance": "Star Wars: Dark Disciple"
 		},
 		{
 			"id": 25,
 			"isMain": true,
 			"name": "Sheev Palpatine/Darth Sidious",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/8/8f/Emperor_RotJ.png/330px-Emperor_RotJ.png",
 			"gender": "male",
 			"first_appearance": "The Empire Strikes Back",
 			"last_appearance": "Star Wars: The Rise of Skywalker"

 		},
 		{
 			"id": 26,
 			"isMain": true,
 			"name": "Finn",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/2/2a/Finn-Force_Awakens_%282015%29.png",
 			"gender": "male",
 			"first_appearance": "Star Wars: The Force Awakens"
 		},
 		{
 			"id": 27,
 			"isMain": true,
 			"name": "Maz Kanata",
 			"image_url": "https://pm1.narvii.com/6314/59d6b3b421a2505ba5cb3717040d6f77fcd81a5a_128.jpg",
 			"gender": "female",
 			"first_appearance": "Star Wars: The Force Awakens"
 		},
 		{
 			"id": 28,
 			"isMain": true,
 			"name": "Rey Palpatine (adota o sobrenome Skywalker)",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 29,
 			"isMain": true,
 			"name": "Ben Solo/Kylo Ren",
 			"image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgD4TrHfW25eI3KSB0EWqOlV0pOQekjj1RCiQdyWAhOEmXxh-Y&s",
 			"gender": ""
 		},
 		{
 			"id": 30,
 			"isMain": true,
 			"name": "Líder Supremo Snoke",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Snoke-The_Force_Awakens_%282015%29.png/330px-Snoke-The_Force_Awakens_%282015%29.png",
 			"gender": "male",
 			"first_appearance": "The Force Awaken",
 			"last_appearance": "Star Wars: The Rise of Skywalker"
 		},
 		{
 			"id": 31,
 			"isMain": false,
 			"name": "Aayla Secura",
 			"image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/4/4d/Aayla_Secura_SWE.png/revision/latest/top-crop/width/720/height/900?cb=20190710143154",
 			"gender": ""
 		},
 		{
 			"id": 32,
 			"isMain": false,
 			"name": "Adi Gallia",
 			"image_url": "https://pbs.twimg.com/profile_images/3534990862/da15c2223f00bef1ba8104a86acfa959.jpeg",
 			"gender": ""
 		},
 		{
 			"id": 33,
 			"isMain": false,
 			"name": "Agen Kolar",
 			"image_url": "https://pm1.narvii.com/6120/dbf3f6d657b1e3633440cb7615c54ae8bb37e094_128.jpg",
 			"gender": ""
 		},
 		{
 			"id": 34,
 			"isMain": false,
 			"name": "Almirante Firmus Piett",
 			"image_url": "https://pm1.narvii.com/6441/87b607bb18ff99041bbd894aa9a56b6128170ea9_128.jpg",
 			"gender": ""
 		},
 		{
 			"id": 35,
 			"isMain": false,
 			"name": "Antilles",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 36,
 			"isMain": false,
 			"name": "Bail Organa",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 37,
 			"isMain": false,
 			"name": "Barriss Offe",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 38,
 			"isMain": false,
 			"name": "Bib Fortuna",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 39,
 			"isMain": false,
 			"name": "Biggs",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 40,
 			"isMain": false,
 			"name": "Bossk",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 41,
 			"isMain": false,
 			"name": "Capitão Antilles",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 42,
 			"isMain": false,
 			"name": "Capitão Tarpals",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 43,
 			"isMain": false,
 			"name": "Chanceler Valorum",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 44,
 			"isMain": false,
 			"name": "Chefe Nass",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 45,
 			"isMain": false,
 			"name": "Coleman Trebor",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 46,
 			"isMain": false,
 			"name": "Dengar",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 47,
 			"isMain": false,
 			"name": "Depa Billaba",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 48,
 			"isMain": false,
 			"name": "E-3PO",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 49,
 			"isMain": false,
 			"name": "Eeth Koth",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 50,
 			"isMain": false,
 			"name": "Even Piell",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 51,
 			"isMain": false,
 			"name": "Galen Marek",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 52,
 			"isMain": false,
 			"name": "Gardulla",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 53,
 			"isMain": false,
 			"name": "General Hux",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/a/a4/General_Hux_Star_Wars.jpg",
 			"gender": ""
 		},
 		{
 			"id": 54,
 			"isMain": false,
 			"name": "General Veers",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 55,
 			"isMain": false,
 			"name": "Grand Moff Tarkin",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Grand_Moff_Tarkin.png/330px-Grand_Moff_Tarkin.png",
 			"gender": ""
 		},
 		{
 			"id": 56,
 			"isMain": false,
 			"name": "Greedo",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 57,
 			"isMain": false,
 			"name": "IG-88B",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 58,
 			"isMain": false,
 			"name": "IG-88",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 59,
 			"isMain": false,
 			"name": "Jar Jar Binks",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/4/4b/Jjportrait.jpg",
 			"gender": ""
 		},
 		{
 			"id": 60,
 			"isMain": false,
 			"name": "Jocasta Nu",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 61,
 			"isMain": false,
 			"name": "Ki-Adi-Mundi",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 62,
 			"isMain": false,
 			"name": "Kit Fisto",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 63,
 			"isMain": false,
 			"name": "Lobot",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 64,
 			"isMain": false,
 			"name": "Luminara Unduli",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 65,
 			"isMain": false,
 			"name": "Mon Mothma",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 66,
 			"isMain": false,
 			"name": "Nebit",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 67,
 			"isMain": false,
 			"name": "OOM-9",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 68,
 			"isMain": false,
 			"name": "Oppo Rancisis",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 69,
 			"isMain": false,
 			"name": "Plo Koon",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 70,
 			"isMain": false,
 			"name": "Porg",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 71,
 			"isMain": false,
 			"name": "R2-Q5",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 72,
 			"isMain": false,
 			"name": "R4-F5",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 73,
 			"isMain": false,
 			"name": "R4-M9",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 74,
 			"isMain": false,
 			"name": "R4-P17",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 75,
 			"isMain": false,
 			"name": "R5-D4",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 76,
 			"isMain": false,
 			"name": "Rose Tico",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 77,
 			"isMain": false,
 			"name": "Rune Hako",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 78,
 			"isMain": false,
 			"name": "Saesee Tiin",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 79,
 			"isMain": false,
 			"name": "Shaak Ti",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 80,
 			"isMain": false,
 			"name": "Shmi Skywalker",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 81,
 			"isMain": false,
 			"name": "Shu Mai",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 82,
 			"isMain": false,
 			"name": "Stass Allie",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 83,
 			"isMain": false,
 			"name": "TC-14",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 84,
 			"isMain": false,
 			"name": "Tarfull",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 85,
 			"isMain": false,
 			"name": "U-3PO",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 86,
 			"isMain": false,
 			"name": "Vice Rainha Swea Gunray",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 87,
 			"isMain": false,
 			"name": "Vice-Rei Nute Gunray",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 88,
 			"isMain": false,
 			"name": "Wat Tambor",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 89,
 			"isMain": false,
 			"name": "Watto",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 90,
 			"isMain": false,
 			"name": "Wicket",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 91,
 			"isMain": false,
 			"name": "Wullf Yularen",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 92,
 			"isMain": false,
 			"name": "Yaddle",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 93,
 			"isMain": false,
 			"name": "Yarael Poof",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 94,
 			"isMain": false,
 			"name": "Zuckuss",
 			"image_url": "",
 			"gender": ""
 		}
 	]
 }
 """

const val getFilms = """ 
{
    "total_count" : 9,
    "items": [
      {
        "id": 0,
        "name" : "A New Hope",
        "complete_name": "Star Wars: Episode IV - A New Hope (1977)",
        "launch_date": "1977-05-25",
        "character_ids": [4,10,5,55,3,8,7,11,0],
        "synopsis" : "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
        "director" : "George Lucas",
        "cover_album" : "https://www.imdb.com/title/tt0076759/mediaviewer/rm3263717120",
        "trailer" : "https://www.youtube.com/watch?v=1g3_CFmnU7k"


      },
      {
        "id": 1,
        "name" : "The Empire Strikes Back",
        "complete_name": "Star Wars: Episode V - The Empire Strikes Back (1980)",
        "launch_date": "1980-05-21",
        "character_ids": [4,10,5,8,0,11,7,6,3,20,63],
        "synopsis" : "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
        "director": "Irvin Kershner",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,641,1000_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=JNwNXF9Y6kY"

      },
      {
        "id": 2,
        "name" : "Return of the Jedi",
        "complete_name": "Star Wars: Episode VI - Return of the Jedi (1983)",
        "launch_date": "1983-05-25",
        "character_ids": [4,10,5,18,8,11,0,6,3,7,38,65,90,20],
        "synopsis" : "After a daring mission to rescue Han Solo from Jabba the Hutt, the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, Luke struggles to help Darth Vader back from the dark side without falling into the Emperor's trap.",
        "director": "Richard Marquand",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY999_CR0,0,644,999_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=5UfA_aKBGMc"
      },
      {
        "id": 3,
        "name" : "The Phantom Menace",
        "complete_name": "Star Wars: Episode I - The Phantom Menace (1999)",
        "launch_date": "1999-05-19",
        "character_ids": [0],
        "synopsis" : "Two Jedi escape a hostile blockade to find allies and come across a young boy who may bring balance to the Force, but the long dormant Sith resurface to claim their old glory.",
        "director": "George Lucas",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY999_SX666_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=UihVSUYYDTQ"
      },
      {
        "id": 4,
        "name" : "Attack of the Clones",
        "complete_name": "Star Wars: Episode II - Attack of the Clones (2002)",
        "launch_date": "16 de maio de 2002",
        "character_ids": [0],
        "synopsis" : "Ten years after initially meeting, Anakin Skywalker shares a forbidden romance with Padmé Amidala, while Obi-Wan Kenobi investigates an assassination attempt on the senator and discovers a secret clone army crafted for the Jedi.",
        "director": "George Lucas",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BMDAzM2M0Y2UtZjRmZi00MzVlLTg4MjEtOTE3NzU5ZDVlMTU5XkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_SY999_CR0,0,659,999_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=gYbW1F_c9eM"
      },
      {
        "id": 5,
        "name" : "Revenge of the Sith",
        "complete_name": "Star Wars: Episode III - Revenge of the Sith (2005)",
        "launch_date": "2005-05-19",
        "character_ids": [0],
        "synopsis" : "Three years into the Clone Wars, the Jedi rescue Palpatine from Count Dooku. As Obi-Wan pursues a new threat, Anakin acts as a double agent between the Jedi Council and Palpatine and is lured into a sinister plan to rule the galaxy.",
        "director": "George Lucas",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BNTc4MTc3NTQ5OF5BMl5BanBnXkFtZTcwOTg0NjI4NA@@._V1_SY1000_SX750_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=5UnjrG_N8hU"
      },
      {
        "id": 6,
        "name" : "The Force Awakens",
        "complete_name": "Star Wars: Episode VII - The Force Awakens (2015)",
        "launch_date": "2015-12-18",
        "character_ids": [0],
        "synopsis" : "Three decades after the Empire's defeat, a new threat arises in the militant First Order. Defected stormtrooper Finn and the scavenger Rey are caught up in the Resistance's search for the missing Luke Skywalker.",
        "director": "J.J. Abrams",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BOTAzODEzNDAzMl5BMl5BanBnXkFtZTgwMDU1MTgzNzE@._V1_SY1000_CR0,0,677,1000_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=sGbxmsDFVnE"
      },
      {
        "id": 7,
        "name" : "The Last Jedi",
        "complete_name": "Star Wars: Episode VIII - The Last Jedi (2017)",
        "launch_date": "2017-12-14",
        "character_ids": [0],
        "synopsis" : " Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares for battle with the First Order.",
        "director": "Rian Johnson",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BMjQ1MzcxNjg4N15BMl5BanBnXkFtZTgwNzgwMjY4MzI@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=Q0CbN8sfihY"
      },
      {
        "id": 8,
        "name" : "The Rise of Skywalker",
        "complete_name": "Star Wars: Episode IX - The Rise of Skywalker (2019)",
        "launch_date": "2019-12-19",
        "character_ids": [0],
        "synopsis" : "The surviving members of the resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak bringing the Skywalker saga to its end.",
        "director": "J.J. Abrams",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=8Qn_spdM5Zg"
      }
    ]
  }
"""
