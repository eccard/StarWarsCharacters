package com.eccard.starwarscharacters.data.api

class FilmResponseMock {
    companion object {
        fun getFilms() = """
{
    "total_count" : 9,
    "items": [
      {
        "id": 0,
        "name" : "A New Hope",
        "complete_name": "Star Wars: Episode IV - A New Hope (1977)",
        "launch_date": "1977-05-25",
        "character_ids": [1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,18,19,81],
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
        "character_ids": [1,2,3,4,5,10,13,14,18,20,21,22,23,24,25,26],
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
        "character_ids": [1,2,3,4,5,10,13,14,16,18,20,21,22,25,27,28,29,30,31,45],
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
        "character_ids": [2,3,10,11,16,20,21,32,33,34,35,36,37,38,39,40,41,42,43,44,46,47,48,49,50,51,52,53,54,55,56,57,58,59,94,95],
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
        "character_ids": [2,3,6,7,10,11,20,21,22,33,35,36,40,43,46,51,52,53,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,82,94],
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
        "character_ids": [1,2,3,4,5,6,7,10,11,12,13,20,21,33,35,46,51,52,53,54,55,56,58,63,64,67,68,75,78,79,80,81,82,83,93],
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
        "character_ids": [14,1,5,84,85,86,87,88,89,2,13,89],
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
        "character_ids": [1,5,84,85,86,87,88,89,2,91,92,20,13,3,90],
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
        "character_ids": [5,1,84,85,86,2,89,87,13,91,21,25,3,90,11,51,10,46,55,32,20,14],
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