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
    "total_count" : 95,
    "items": [
      {
        "id": 0,
        "isMain": true,
        "name": "Anakin Skywalker/Darth Vader",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/7/76/Darth_Vader.jpg"
      },
      {
        "id": 2,
        "isMain": true,
        "name": "Ben Solo/Kylo Ren",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/3/34/Kylo_Ren.png/330px-Kylo_Ren.png"
      },
      {
        "id": 3,
        "isMain": true,
        "name": "Obi-Wan Kenobi",
        "image_url": "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816"
      },
      {
        "id": 4,
        "isMain": true,
        "name": "Luke Skywalker",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png"
      },
      {
        "id": 5,
        "isMain": true,
        "name": "Leia Organa",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg"
      },
      {
        "id": 6,
        "isMain": true,
        "name": "Yoda",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Yoda_Empire_Strikes_Back.png"
      },
      {
        "id": 7,
        "isMain": true,
        "name": "R2-D2",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/R2-D2_Droid.png"
      },
      {
        "id": 8,
        "isMain": true,
        "name": "C-3PO",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/5/5c/C-3PO_droid.png"
      },
      {
        "id": 9,
        "isMain": true,
        "name": "BB-8",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/BB-8%2C_Star_Wars_The_Force_Awakens.jpg"
      },
      {
        "id": 10,
        "isMain": true,
        "name": "Han Solo",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg"
      },
      {
        "id": 11,
        "isMain": true,
        "name": "Chewbacca",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/6/6d/Chewbacca-2-.jpg"
      },
      {
        "id": 12,
        "isMain": true,
        "name": "Padmé Amidala",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/ee/Amidala.png/220px-Amidala.png"
      },
      {
        "id": 13,
        "isMain": true,
        "name": "Mace Windu",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Mace_Windu.png"
      },
      {
        "id": 14,
        "isMain": true,
        "name": "Qui-Gon Jinn",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/a/ad/Qui-Gon_Jinn.png/220px-Qui-Gon_Jinn.png"
      },
      {
        "id": 15,
        "isMain": true,
        "name": "Vice Almirante Holdo",
        "image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/a/a4/Holdo-Elle.png/revision/latest?cb=20171214221607"
      },
      {
        "id": 16,
        "isMain": true,
        "name": "Rose Tico",
        "image_url": "https://pm1.narvii.com/6521/766f393132d82c1087e11e1e505ccd464b5188da_128.jpg"
      },
      {
        "id": 17,
        "isMain": true,
        "name": "Poe Dameron",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/0/0b/Poe_Dameron-Force_Awakens_%282015%29.png"
      },
      {
        "id": 18,
        "isMain": true,
        "name": "Lando Calrissian",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Lando6-2.jpg/330px-Lando6-2.jpg"
      },
      {
        "id": 19,
        "isMain": true,
        "name": "Darth Maul",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Darth_Maul.png"
      },
      {
        "id": 20,
        "isMain": true,
        "name": "Boba Fett",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png"
      },
      {
        "id": 21,
        "isMain": true,
        "name": "Jabba, the Hutt",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png/330px-Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png"
      },
      {
        "id": 22,
        "isMain": true,
        "name": "Jango Fett",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Jango_Fett.png/330px-Jango_Fett.png"
      },
      {
        "id": 23,
        "isMain": true,
        "name": "Conde Dooku/Darth Tyranus",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/b/bd/Count_Dooku.png/330px-Count_Dooku.png"
      },
      {
        "id": 24,
        "isMain": true,
        "name": "General Grievous",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/GeneralGrievous.png/220px-GeneralGrievous.png"
      },
      {
        "id": 25,
        "isMain": true,
        "name": "Sheev Palpatine/Darth Sidious",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/8/8f/Emperor_RotJ.png/330px-Emperor_RotJ.png"
      },
      {
        "id": 26,
        "isMain": true,
        "name": "Finn",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/2/2a/Finn-Force_Awakens_%282015%29.png"
      },
      {
        "id": 27,
        "isMain": true,
        "name": "Maz Kanata",
        "image_url": "https://pm1.narvii.com/6314/59d6b3b421a2505ba5cb3717040d6f77fcd81a5a_128.jpg"
      },
      {
        "id": 28,
        "isMain": true,
        "name": "Rey Palpatine (adota o sobrenome Skywalker)",
        "image_url": ""
      },
      {
        "id": 29,
        "isMain": true,
        "name": "Ben Solo/Kylo Ren",
        "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgD4TrHfW25eI3KSB0EWqOlV0pOQekjj1RCiQdyWAhOEmXxh-Y&s"
      },
      {
        "id": 30,
        "isMain": true,
        "name": "Líder Supremo Snoke",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Snoke-The_Force_Awakens_%282015%29.png/330px-Snoke-The_Force_Awakens_%282015%29.png"
      },
      {
        "id": 31,
        "isMain": false,
        "name": "Aayla Secura",
        "image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/4/4d/Aayla_Secura_SWE.png/revision/latest/top-crop/width/720/height/900?cb=20190710143154"
      },
      {
        "id": 32,
        "isMain": false,
        "name": "Adi Gallia",
        "image_url": "https://pbs.twimg.com/profile_images/3534990862/da15c2223f00bef1ba8104a86acfa959.jpeg"
      },
      {
        "id": 33,
        "isMain": false,
        "name": "Agen Kolar",
        "image_url": "https://pm1.narvii.com/6120/dbf3f6d657b1e3633440cb7615c54ae8bb37e094_128.jpg"
      },
      {
        "id": 34,
        "isMain": false,
        "name": "Almirante Firmus Piett",
        "image_url": "https://pm1.narvii.com/6441/87b607bb18ff99041bbd894aa9a56b6128170ea9_128.jpg"
      },
      {
        "id": 35,
        "isMain": false,
        "name": "Antilles",
        "image_url": ""
      },
      {
        "id": 36,
        "isMain": false,
        "name": "Bail Organa",
        "image_url": ""
      },
      {
        "id": 37,
        "isMain": false,
        "name": "Barriss Offe",
        "image_url": ""
      },
      {
        "id": 38,
        "isMain": false,
        "name": "Bib Fortuna",
        "image_url": ""
      },
      {
        "id": 39,
        "isMain": false,
        "name": "Biggs",
        "image_url": ""
      },
      {
        "id": 40,
        "isMain": false,
        "name": "Bossk",
        "image_url": ""
      },
      {
        "id": 41,
        "isMain": false,
        "name": "Capitão Antilles",
        "image_url": ""
      },
      {
        "id": 42,
        "isMain": false,
        "name": "Capitão Tarpals",
        "image_url": ""
      },
      {
        "id": 43,
        "isMain": false,
        "name": "Chanceler Valorum",
        "image_url": ""
      },
      {
        "id": 44,
        "isMain": false,
        "name": "Chefe Nass",
        "image_url": ""
      },
      {
        "id": 45,
        "isMain": false,
        "name": "Coleman Trebor",
        "image_url": ""
      },
      {
        "id": 46,
        "isMain": false,
        "name": "Dengar",
        "image_url": ""
      },
      {
        "id": 47,
        "isMain": false,
        "name": "Depa Billaba",
        "image_url": ""
      },
      {
        "id": 48,
        "isMain": false,
        "name": "E-3PO",
        "image_url": ""
      },
      {
        "id": 49,
        "isMain": false,
        "name": "Eeth Koth",
        "image_url": ""
      },
      {
        "id": 50,
        "isMain": false,
        "name": "Even Piell",
        "image_url": ""
      },
      {
        "id": 51,
        "isMain": false,
        "name": "Galen Marek",
        "image_url": ""
      },
      {
        "id": 52,
        "isMain": false,
        "name": "Gardulla",
        "image_url": ""
      },
      {
        "id": 53,
        "isMain": false,
        "name": "General Hux",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/a/a4/General_Hux_Star_Wars.jpg"
      },
      {
        "id": 54,
        "isMain": false,
        "name": "General Veers",
        "image_url": ""
      },
      {
        "id": 55,
        "isMain": false,
        "name": "Grand Moff Tarkin",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Grand_Moff_Tarkin.png/330px-Grand_Moff_Tarkin.png"
      },
      {
        "id": 56,
        "isMain": false,
        "name": "Greedo",
        "image_url": ""
      },
      {
        "id": 57,
        "isMain": false,
        "name": "IG-88B",
        "image_url": ""
      },
      {
        "id": 58,
        "isMain": false,
        "name": "IG-88",
        "image_url": ""
      },
      {
        "id": 59,
        "isMain": false,
        "name": "Jar Jar Binks",
        "image_url": "https://upload.wikimedia.org/wikipedia/en/4/4b/Jjportrait.jpg"
      },
      {
        "id": 60,
        "isMain": false,
        "name": "Jocasta Nu",
        "image_url": ""
      },
      {
        "id": 61,
        "isMain": false,
        "name": "Ki-Adi-Mundi",
        "image_url": ""
      },
      {
        "id": 62,
        "isMain": false,
        "name": "Kit Fisto",
        "image_url": ""
      },
      {
        "id": 63,
        "isMain": false,
        "name": "Lobot",
        "image_url": ""
      },
      {
        "id": 64,
        "isMain": false,
        "name": "Luminara Unduli",
        "image_url": ""
      },
      {
        "id": 65,
        "isMain": false,
        "name": "Mon Mothma",
        "image_url": ""
      },
      {
        "id": 66,
        "isMain": false,
        "name": "Nebit",
        "image_url": ""
      },
      {
        "id": 67,
        "isMain": false,
        "name": "OOM-9",
        "image_url": ""
      },
      {
        "id": 68,
        "isMain": false,
        "name": "Oppo Rancisis",
        "image_url": ""
      },
      {
        "id": 69,
        "isMain": false,
        "name": "Plo Koon",
        "image_url": ""
      },
      {
        "id": 70,
        "isMain": false,
        "name": "Porg",
        "image_url": ""
      },
      {
        "id": 71,
        "isMain": false,
        "name": "R2-Q5",
        "image_url": ""
      },
      {
        "id": 72,
        "isMain": false,
        "name": "R4-F5",
        "image_url": ""
      },
      {
        "id": 73,
        "isMain": false,
        "name": "R4-M9",
        "image_url": ""
      },
      {
        "id": 74,
        "isMain": false,
        "name": "R4-P17",
        "image_url": ""
      },
      {
        "id": 75,
        "isMain": false,
        "name": "R5-D4",
        "image_url": ""
      },
      {
        "id": 76,
        "isMain": false,
        "name": "Rose Tico",
        "image_url": ""
      },
      {
        "id": 77,
        "isMain": false,
        "name": "Rune Hako",
        "image_url": ""
      },
      {
        "id": 78,
        "isMain": false,
        "name": "Saesee Tiin",
        "image_url": ""
      },
      {
        "id": 79,
        "isMain": false,
        "name": "Shaak Ti",
        "image_url": ""
      },
      {
        "id": 80,
        "isMain": false,
        "name": "Shmi Skywalker",
        "image_url": ""
      },
      {
        "id": 81,
        "isMain": false,
        "name": "Shu Mai",
        "image_url": ""
      },
      {
        "id": 82,
        "isMain": false,
        "name": "Stass Allie",
        "image_url": ""
      },
      {
        "id": 83,
        "isMain": false,
        "name": "TC-14",
        "image_url": ""
      },
      {
        "id": 84,
        "isMain": false,
        "name": "Tarfull",
        "image_url": ""
      },
      {
        "id": 85,
        "isMain": false,
        "name": "U-3PO",
        "image_url": ""
      },
      {
        "id": 86,
        "isMain": false,
        "name": "Vice Rainha Swea Gunray",
        "image_url": ""
      },
      {
        "id": 87,
        "isMain": false,
        "name": "Vice-Rei Nute Gunray",
        "image_url": ""
      },
      {
        "id": 88,
        "isMain": false,
        "name": "Wat Tambor",
        "image_url": ""
      },
      {
        "id": 89,
        "isMain": false,
        "name": "Watto",
        "image_url": ""
      },
      {
        "id": 90,
        "isMain": false,
        "name": "Wicket",
        "image_url": ""
      },
      {
        "id": 91,
        "isMain": false,
        "name": "Wullf Yularen",
        "image_url": ""
      },
      {
        "id": 92,
        "isMain": false,
        "name": "Yaddle",
        "image_url": ""
      },
      {
        "id": 93,
        "isMain": false,
        "name": "Yarael Poof",
        "image_url": ""
      },
      {
        "id": 94,
        "isMain": false,
        "name": "Zuckuss",
        "image_url": ""
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
        "name" : "Uma Nova Esperança",
        "complete_name": "Star Wars: Episódio IV - Uma Nova Esperança",
        "date": "25 de maio de 1977",
        "character_ids": [4,10,5,55,3,8,7,11,0]
      },
      {
        "id": 1,
        "name" : "O Império Contra-Ataca",
        "complete_name": "Star Wars: Episódio V - O Império Contra-Ataca",
        "date": "21 de maio de 1980",
        "character_ids": [4,10,5,8,0,11,7,6,3,20,63]
      },
      {
        "id": 2,
        "name" : "O Retorno de Jedi",
        "complete_name": "Star Wars: Episódio VI - O Retorno de Jedi",
        "date": "25 de maio de 1983 ",
        "character_ids": [4,10,5,18,8,11,0,6,3,7,38,65,90,20]
      },
      {
        "id": 3,
        "name" : "A Ameaça Fantasma",
        "complete_name": "Star Wars: Episódio I - A Ameaça Fantasma",
        "date": "19 de maio de 1999",
        "character_ids": [0]
      },
      {
        "id": 4,
        "name" : "Ataque dos Clones",
        "complete_name": "Star Wars: Episódio II - Ataque dos Clones",
        "date": "16 de maio de 2002",
        "character_ids": [0]
      },
      {
        "id": 5,
        "name" : "A Vingança dos Sith",
        "complete_name": "Star Wars: Episódio III - A Vingança dos Sith",
        "date": "19 de maio de 2005",
        "character_ids": [0]
      },
      {
        "id": 6,
        "name" : "O Despertar da Força",
        "complete_name": "Star Wars: Episódio VII - O Despertar da Força",
        "date": "18 de dezembro de 2015",
        "character_ids": [0]
      },
      {
        "id": 7,
        "name" : "Os Últimos Jedi",
        "complete_name": "Star Wars: Episódio VIII - Os Últimos Jedi",
        "date": "14 de dezembro de 2017",
        "character_ids": [0]
      },
      {
        "id": 8,
        "name" : "A Ascensão Skywalker",
        "complete_name": "Star Wars: Episódio IX - A Ascensão Skywalker",
        "date": "19 de dezembro de 2019 ",
        "character_ids": [0]
      }
    ]
  }
"""