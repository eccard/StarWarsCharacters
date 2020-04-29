package com.eccard.starwarscharacters.data.api

class FilmResponseMock {
    companion object {
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
        "character_ids": [0,14,3,12,80,59,8,7,6,43,19,42,13,83],
        "synopsis" : "Two Jedi escape a hostile blockade to find allies and come across a young boy who may bring balance to the Force, but the long dormant Sith resurface to claim their old glory.",
        "director": "George Lucas",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY999_SX666_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=bD7bpG-zDJQ"
      },
      {
        "id": 4,
        "name" : "Attack of the Clones",
        "complete_name": "Star Wars: Episode II - Attack of the Clones (2002)",
        "launch_date": "16 de maio de 2002",
        "character_ids": [0,3,12,23,13,6,80,22,36,59,89,8,61,20,7],
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
        "character_ids": [3,12,0,25,13,36,6,8,23,61,55,35,7,11,33,78,31,24],
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
        "character_ids": [10,4,5,2,26,17,27,30,53,8,11],
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
        "character_ids": [4,5,2,26,17,30,53,8,16,15,6,11,7,9],
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
        "character_ids": [5,4,2,26,17,8,53,27,76,25,18,7,9,27,0,35,30,64,13,31,3,32,6,32,14,10],
        "synopsis" : "The surviving members of the resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak bringing the Skywalker saga to its end.",
        "director": "J.J. Abrams",
        "cover_album" : "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
        "trailer" : "https://www.youtube.com/watch?v=8Qn_spdM5Zg"
      }
    ]
  }
"""

    }
}