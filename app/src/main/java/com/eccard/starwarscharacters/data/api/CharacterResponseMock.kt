package com.eccard.starwarscharacters.data.api

class CharacterResponseMock {
    companion object {
        const val getCharacters = """
{
 	"total_count": 95,
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
 		},
 		{
 			"id": 3,
 			"isMain": true,
 			"name": "Obi-Wan Kenobi",
 			"image_url": "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816",
 			"gender": "male"
 		},
 		{
 			"id": 4,
 			"isMain": true,
 			"name": "Luke Skywalker",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png",
 			"gender": "male"
 		},
 		{
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
 			"id": 7,
 			"isMain": true,
 			"name": "R2-D2",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/R2-D2_Droid.png",
 			"gender": ""
 		},
 		{
 			"id": 8,
 			"isMain": true,
 			"name": "C-3PO",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/5/5c/C-3PO_droid.png",
 			"gender": ""
 		},
 		{
 			"id": 9,
 			"isMain": true,
 			"name": "BB-8",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/BB-8%2C_Star_Wars_The_Force_Awakens.jpg",
 			"gender": ""
 		},
 		{
 			"id": 10,
 			"isMain": true,
 			"name": "Han Solo",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg",
 			"gender": "male"
 		},
 		{
 			"id": 11,
 			"isMain": true,
 			"name": "Chewbacca",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/6/6d/Chewbacca-2-.jpg",
 			"gender": "male"
 		},
 		{
 			"id": 12,
 			"isMain": true,
 			"name": "Padmé Amidala",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/ee/Amidala.png/220px-Amidala.png",
 			"gender": "female"
 		},
 		{
 			"id": 13,
 			"isMain": true,
 			"name": "Mace Windu",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Mace_Windu.png",
 			"gender": "male"
 		},
 		{
 			"id": 14,
 			"isMain": true,
 			"name": "Qui-Gon Jinn",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/a/ad/Qui-Gon_Jinn.png/220px-Qui-Gon_Jinn.png",
 			"gender": "male"
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
 			"gender": "female"
 		},
 		{
 			"id": 17,
 			"isMain": true,
 			"name": "Poe Dameron",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/0/0b/Poe_Dameron-Force_Awakens_%282015%29.png",
 			"gender": "male"
 		},
 		{
 			"id": 18,
 			"isMain": true,
 			"name": "Lando Calrissian",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Lando6-2.jpg/330px-Lando6-2.jpg",
 			"gender": "male"
 		},
 		{
 			"id": 19,
 			"isMain": true,
 			"name": "Darth Maul",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Darth_Maul.png",
 			"gender": "male"
 		},
 		{
 			"id": 20,
 			"isMain": true,
 			"name": "Boba Fett",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png",
 			"gender": "male"
 		},
 		{
 			"id": 21,
 			"isMain": true,
 			"name": "Jabba, the Hutt",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png/330px-Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png",
 			"gender": "male"
 		},
 		{
 			"id": 22,
 			"isMain": true,
 			"name": "Jango Fett",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Jango_Fett.png/330px-Jango_Fett.png",
 			"gender": "male"
 		},
 		{
 			"id": 23,
 			"isMain": true,
 			"name": "Conde Dooku/Darth Tyranus",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/b/bd/Count_Dooku.png/330px-Count_Dooku.png",
 			"gender": "male"
 		},
 		{
 			"id": 24,
 			"isMain": true,
 			"name": "General Grievous",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/GeneralGrievous.png/220px-GeneralGrievous.png",
 			"gender": "male"
 		},
 		{
 			"id": 25,
 			"isMain": true,
 			"name": "Sheev Palpatine/Darth Sidious",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/8/8f/Emperor_RotJ.png/330px-Emperor_RotJ.png",
 			"gender": "male"
 		},
 		{
 			"id": 26,
 			"isMain": true,
 			"name": "Finn",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/2/2a/Finn-Force_Awakens_%282015%29.png",
 			"gender": "male"
 		},
 		{
 			"id": 27,
 			"isMain": true,
 			"name": "Maz Kanata",
 			"image_url": "https://pm1.narvii.com/6314/59d6b3b421a2505ba5cb3717040d6f77fcd81a5a_128.jpg",
 			"gender": "female"
 		},
 		{
 			"id": 28,
 			"isMain": true,
 			"name": "Rey Palpatine (adota o sobrenome Skywalker)",
 			"image_url": "",
 			"gender": ""
 		},
 		{
 			"id": 30,
 			"isMain": true,
 			"name": "Supreme Leader Snoke",
 			"image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Snoke-The_Force_Awakens_%282015%29.png/330px-Snoke-The_Force_Awakens_%282015%29.png",
 			"gender": "male"
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
 			"image_url": "https://m.media-amazon.com/images/M/MV5BMTMxNzI5OTY3NF5BMl5BanBnXkFtZTcwOTEwNTI4NA@@._V1_SY1000_CR0,0,651,1000_AL_.jpg",
 			"gender": "male"
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
 			"gender": "male"
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
 			"image_url": "https://m.media-amazon.com/images/M/MV5BMTMxNTEzMTkyM15BMl5BanBnXkFtZTcwNzU1NzI4NA@@._V1_SY1000_CR0,0,664,1000_AL_.jpg",
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
 			"image_url": "https://m.media-amazon.com/images/M/MV5BMTg3MTM5ODA3OV5BMl5BanBnXkFtZTcwMTA2NzI4NA@@._V1_.jpg",
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
 			"image_url": "https://m.media-amazon.com/images/M/MV5BMjE0MDM2MzkzOF5BMl5BanBnXkFtZTcwNTkyMzA4NA@@._V1_SY1000_SX1500_AL_.jpg",
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

    }
}