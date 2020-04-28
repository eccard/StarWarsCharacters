package com.eccard.starwarscharacters.util.com.eccard.starwarscharacters.util

import com.eccard.starwarscharacters.data.api.CharactterResponse
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.model.Charactter
import com.google.gson.Gson

fun getCharacterThatIsInFilm() : List<Charactter> {
    return Gson().fromJson(
        """
                {
                    "total_count": 3,
                    "items": [{
                            "id": 5,
                            "isMain": true,
                            "name": "Leia Organa",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg",
                            "gender": "female"
                        },
                        {
                            "id": 6,
                            "isMain": true,
                            "name": "Yoda",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Yoda_Empire_Strikes_Back.png",
                            "gender": "male"
                        },
                        {
                            "id": 20,
                            "isMain": true,
                            "name": "Boba Fett",
                            "image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png",
                            "gender": "male"
                        }
                    ]
                }
                """
        , CharactterResponse::class.java).items
}



fun getCharacterResponse() : CharactterResponse{
    return Gson().fromJson(
        """
                {
                  "total_count": 2,
                  "items": [{
                    "id": 0,
                    "isMain": true,
                    "name": "Anakin Skywalker, Darth Vader",
                    "image_url": "https://66.media.tumblr.com/9856bd42f18ce548f05d1c2701d298be/tumblr_pxqwf3OeH81w4t7wqo1_500.jpg",
                    "gender": "male"
                    },
                    {
                      "id": 2,
                      "isMain": true,
                      "name": "Ben Solo, Kylo Ren",
                      "image_url": "https://i.pinimg.com/236x/71/06/dc/7106dc5edb54d56cb15492e1772fbec5.jpg",
                      "gender": "male"
                    }
                  ]
                }
            """,CharactterResponse::class.java)
}

fun getFilmResponse() : FilmRespose {

    return Gson().fromJson(
        """
                {
                  "total_count" : 2,
                  "items": [
                    {
                      "id": 0,
                      "name" : "A New Hope",
                      "complete_name": "Star Wars: Episode IV - A New Hope (1977)",
                      "launch_date": "1977-05-25",
                      "character_ids": [4,10,5,55,3,8,7,11,0],
                      "synopsis" : "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
                      "director" : "George Lucas",
                      "cover_album" : "https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,643,1000_AL_.jpg",
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
                
                    }]
                }
            """,FilmRespose::class.java)
}