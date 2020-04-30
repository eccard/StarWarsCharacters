package com.eccard.starwarscharacters.data.api

class CharacterResponseMock {
    companion object {
        fun getCharacters() = """
{
    "total_count": 116,
    "items": [
       {
            "id": 1,
            "isMain": true,
            "name": "Luke Skywalker",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png",
            "height": "172",
            "mass": "77",
            "hair_color": "blond",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "19BBY",
            "gender": "male"
        },
        {
            "id": 2,
            "isMain": true,
            "name": "C-3PO",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/5/5c/C-3PO_droid.png",
            "height": "167",
            "mass": "75",
            "hair_color": "n/a",
            "skin_color": "gold",
            "eye_color": "yellow",
            "birth_year": "112BBY",
            "gender": "n/a"
        },
        {
            "id": 3,
            "isMain": true,
            "name": "R2-D2",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/R2-D2_Droid.png",
            "height": "96",
            "mass": "32",
            "hair_color": "n/a",
            "skin_color": "white,blue",
            "eye_color": "red",
            "birth_year": "33BBY",
            "gender": "n/a" 
        },
        {
            "id": 4,
            "isMain": true,
            "name": "Darth Vader",
            "image_url": "https://s2.glbimg.com/Gy1WRmJ6Dao1vPdmsp9AjDpZJkY=/0x0:1905x1051/1262x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2018/q/I/6lI20XSneBNvmN91V8tQ/darth.jpeg",
            "height": "202",
            "mass": "136",
            "hair_color": "none",
            "skin_color": "white",
            "eye_color": "yellow",
            "birth_year": "41.9BBY",
            "gender": "male"
        },
        {
            "id": 5,
            "isMain": true,
            "name": "Leia Organa",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg",
            "height": "150",
            "mass": "49",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "19BBY",
            "gender": "female"
        },
        {
            "id": 6,
            "isMain": true,
            "name": "Owen Lars",
            "image_url": "",
            "height": "178",
            "mass": "120",
            "hair_color": "brown,grey",
            "skin_color": "light",
            "eye_color": "blue",
            "birth_year": "52BBY",
            "gender": "male"

        },
        {
            "id": 7,
            "isMain": true,
            "image_url": "",
            "name": "Beru Whitesun lars",
            "height": "165",
            "mass": "75",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "blue",
            "birth_year": "47BBY",
            "gender": "female"
        },
        {
            "id": 8,
            "isMain": true,
            "image_url": "",
            "name": "R5-D4",
            "height": "97",
            "mass": "32",
            "hair_color": "n/a",
            "skin_color": "white,red",
            "eye_color": "red",
            "birth_year": "unknown",
            "gender": "n/a"
        },
        {
            "id": 9,
            "isMain": true,
            "image_url": "",
            "name": "Biggs Darklighter",
            "height": "183",
            "mass": "84",
            "hair_color": "black",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "24BBY",
            "gender": "male"
        },
        {
            "id": 10,
            "isMain": true,
            "name": "Obi-Wan Kenobi",
            "image_url": "https://vignette.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg/revision/latest/scale-to-width-down/500?cb=20111115052816",
            "height": "182",
            "mass": "77",
            "hair_color": "auburn,white",
            "skin_color": "fair",
            "eye_color": "blue-gray",
            "birth_year": "57BBY",
            "gender": "male"
        },


        {
            "id": 11,
            "isMain": true,
            "image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/6/6f/Anakin_Skywalker_RotS.png/revision/latest/scale-to-width-down/350?cb=20151023200318",
            "name": "Anakin Skywalker",
            "height": "188",
            "mass": "84",
            "hair_color": "blond",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "41.9BBY",
            "gender": "male"
        },
        {
            "id": 12,
            "isMain": false,
            "name": "Grand Moff Tarkin",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Grand_Moff_Tarkin.png/330px-Grand_Moff_Tarkin.png",
            "height": "180",
            "mass": "unknown",
            "hair_color": "auburn,grey",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "64BBY",
            "gender": "male"
        },
        {
            "id": 13,
            "isMain": true,
            "name": "Chewbacca",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/6/6d/Chewbacca-2-.jpg",
            "height": "228",
            "mass": "112",
            "hair_color": "brown",
            "skin_color": "unknown",
            "eye_color": "blue",
            "birth_year": "200BBY",
            "gender": "male"
        },
        {
            "id": 14,
            "isMain": true,
            "name": "Han Solo",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg",
            "height": "180",
            "mass": "80",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "brown",
            "birth_year": "29BBY",
            "gender": "male"
        },
        {
            "id": 15,
            "isMain": false,
            "name": "Greedo",
            "image_url": "",
            "height": "173",
            "mass": "74",
            "hair_color": "n/a",
            "skin_color": "green",
            "eye_color": "black",
            "birth_year": "44BBY",
            "gender": "male"
        },
        {
            "id": 16,
            "isMain": true,
            "name":  "Jabba Desilijic Tiure",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png/330px-Jabba_the_Hutt_in_Return_of_the_Jedi_%281983%29.png",
            "height": "175",
            "mass": "1,358",
            "hair_color": "n/a",
            "skin_color": "green-tan,brown",
            "eye_color": "orange",
            "birth_year": "600BBY",
            "gender": "hermaphrodite"
        },
        {
            "id": 18,
            "isMain": false,
            "name": "Wedge Antilles",
            "image_url": "",
            "height": "170",
            "mass": "77",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "hazel",
            "birth_year": "21BBY",
            "gender": "male"
        },
        {
            "id": 19,
            "isMain": false,
            "name": "Jek Tono Porkins",
            "image_url": "",
            "height": "180",
            "mass": "110",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "male" 
        },
        {
            "id": 20,
            "isMain": true,
            "name": "Yoda",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/9/9b/Yoda_Empire_Strikes_Back.png",
            "height": "66",
            "mass": "17",
            "hair_color": "white",
            "skin_color": "green",
            "eye_color": "brown",
            "birth_year": "896BBY",
            "gender": "male"
        },
        {
            "id": 21,
            "isMain": true,
            "name": "Palpatine",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/8/8f/Emperor_RotJ.png/330px-Emperor_RotJ.png",
            "height": "170",
            "mass": "75",
            "hair_color": "grey",
            "skin_color": "pale",
            "eye_color": "yellow",
            "birth_year": "82BBY",
            "gender": "male"
        },




        {
            "id": 22,
            "isMain": true,
            "name": "Boba Fett",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/3/3e/FettbobaJB.png",
            "height": "183",
            "mass": "78.2",
            "hair_color": "black",
            "skin_color": "fair",
            "eye_color": "brown",
            "birth_year": "31.5BBY",
            "gender": "male"
        },
        {
            "id": 23,
            "isMain": false,
            "name": "IG-88B",
            "image_url": "",
            "height": "200",
            "mass": "140",
            "hair_color": "none",
            "skin_color": "metal",
            "eye_color": "red",
            "birth_year": "15BBY",
            "gender": "none"
        },
        {
            "id": 24,
            "isMain": false,
            "name": "Bossk",
            "image_url": "",
            "height": "190",
            "mass": "113",
            "hair_color": "none",
            "skin_color": "green",
            "eye_color": "red",
            "birth_year": "53BBY",
            "gender": "male"
        },
        {
            "id": 25,
            "isMain": true,
            "name": "Lando Calrissian",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Lando6-2.jpg/330px-Lando6-2.jpg",
            "height": "177",
            "mass": "79",
            "hair_color": "black",
            "skin_color": "dark",
            "eye_color": "brown",
            "birth_year": "31BBY",
            "gender": "male"
        },
        {
            "id": 26,
            "isMain": false,
            "name": "Lobot",
            "image_url": "",
            "height": "175",
            "mass": "79",
            "hair_color": "none",
            "skin_color": "light",
            "eye_color": "blue",
            "birth_year": "37BBY",
            "gender": "male"
        },
        {
            "id": 27,
            "isMain": true,
            "name": "Ackbar",
            "image_url": "",
            "height": "180",
            "mass": "83",
            "hair_color": "none",
            "skin_color": "brown mottle",
            "eye_color": "orange",
            "birth_year": "41BBY",
            "gender": "male"
        },
        {
            "id": 28,
            "isMain": false,
            "name": "Mon Mothma",
            "image_url": "",
            "height": "150",
            "mass": "unknown",
            "hair_color": "auburn",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "48BBY",
            "gender": "female"
        },
        {
            "id": 29,
            "isMain": false,
            "name": "Arvel Crynyd",
            "image_url": "",
            "height": "unknown",
            "mass": "unknown",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 30,
            "isMain": false,
            "name": "Wicket Systri Warrick",
            "image_url": "",
            "height": "88",
            "mass": "20",
            "hair_color": "brown",
            "skin_color": "brown",
            "eye_color": "brown",
            "birth_year": "8BBY",
            "gender": "male"
        },
        {
            "id": 31,
            "isMain": true,
            "name": "Nien Nunb",
            "image_url": "",
            "height": "160",
            "mass": "68",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "male"

        },
       

       
        {
            "id": 32,
            "isMain": true,
            "name": "Qui-Gon Jinn",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/a/ad/Qui-Gon_Jinn.png/220px-Qui-Gon_Jinn.png",
            "height": "193",
            "mass": "89",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "92BBY",
            "gender": "male"
        },
        {
            "id": 33,
            "isMain": false,
            "name": "Nute Gunray",
            "image_url": "",
            "height": "191",
            "mass": "90",
            "hair_color": "none",
            "skin_color": "mottled green",
            "eye_color": "red",
            "birth_year": "unknown",
            "gender": "male"
        },

        {
            "id": 34,
            "isMain": false,
            "name": "Finis Valorum",
            "image_url": "",
            "height": "170",
            "mass": "unknown",
            "hair_color": "blond",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "91BBY",
            "gender": "male"
        },
        {
            "id": 35,
            "isMain": true,
            "name": "Padmé Amidala",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/ee/Amidala.png/220px-Amidala.png",
            "height": "185",
            "mass": "45",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "46BBY",
            "gender": "female"
        },

        {
            "id": 36,
            "isMain": false,
            "name": "Jar Jar Binks",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/4/4b/Jjportrait.jpg",
            "height": "196",
            "mass": "66",
            "hair_color": "none",
            "skin_color": "orange",
            "eye_color": "orange",
            "birth_year": "52BBY",
            "gender": "male"
        },
        {
            "id": 37,
            "isMain": false,
            "name": "Roos Tarpals",
            "image_url": "",
            "height": "224",
            "mass": "82",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "orange",
            "birth_year": "unknown",
            "gender": "male"
        },
               
        {
            "id": 38,
            "isMain": false,
            "name": "Rugor Nass",
            "image_url": "",
            "height": "206",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "green",
            "eye_color": "orange",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 39,
            "isMain": false,
            "name": "Ric Olié",
            "height": "183",
            "mass": "unknown",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 40,
            "isMain": false,
            "name": "Watto",
            "image_url": "",
            "height": "137",
            "mass": "unknown",
            "hair_color": "black",
            "skin_color": "blue,grey",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 41,
            "isMain": false,
            "name": "Sebulba",
            "image_url": "",
            "height": "112",
            "mass": "40",
            "hair_color": "none",
            "skin_color": "grey,red",
            "eye_color": "orange",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 42,
            "isMain": true,
            "name": "Quarsh Panaka",
            "image_url": "",
            "height": "183",
            "mass": "unknown",
            "hair_color": "black",
            "skin_color": "dark",
            "eye_color": "brown",
            "birth_year": "62BBY",
            "gender": "male"
        },
        {
            "id": 43,
            "isMain": true,
            "name": "Shmi Skywalker",
            "image_url": "",
            "height": "163",
            "mass": "unknown",
            "hair_color": "black",
            "skin_color": "fair",
            "eye_color": "brown",
            "birth_year": "72BBY",
            "gender": "female"
        },
        {
            "id": 44,
            "isMain": true,
            "name": "Darth Maul",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Darth_Maul.png",
            "height": "175",
            "mass": "80",
            "hair_color": "none",
            "skin_color": "red",
            "eye_color": "yellow",
            "birth_year": "54BBY",
            "gender": "male"
        },
        {
            "id": 45,
            "isMain": false,
            "name": "Bib Fortuna",
            "image_url": "",
            "height": "180",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "pale",
            "eye_color": "pink",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 46,
            "isMain": false,
            "name": "Ayla Secura",
            "image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/4/4d/Aayla_Secura_SWE.png/revision/latest/top-crop/width/720/height/900?cb=20190710143154",
            "height": "178",
            "mass": "55",
            "hair_color": "none",
            "skin_color": "blue",
            "eye_color": "hazel",
            "birth_year": "48BBY",
            "gender": "female"
        },
        {
            "id": 47,
            "isMain": false,
            "name": "Ratts Tyerel",
            "image_url": "",
            "height": "79",
            "mass": "15",
            "hair_color": "none",
            "skin_color": "grey,blue",
            "eye_color": "unknown",
            "birth_year": "unknown",
            "gender": "male" 
        },
        {
            "id": 48,
            "isMain": false,
            "name": "Dud Bolt",
            "image_url": "",
            "height": "94",
            "mass": "45",
            "hair_color": "none",
            "skin_color": "blue,grey",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "male"

        },
        {
            "id": 49,
            "isMain": false,
            "name": "Gasgano",
            "image_url": "",
            "height": "122",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "white,blue",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 50,
            "isMain": false,
            "name": "Ben Quadinaros",
            "image_url": "",
            "height": "163",
            "mass": "65",
            "hair_color": "none",
            "skin_color": "grey,green,yellow",
            "eye_color": "orange",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 51,
            "isMain": true,
            "name": "Mace Windu",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/b/bf/Mace_Windu.png",
            "height": "188",
            "mass": "84",
            "hair_color": "none",
            "skin_color": "dark",
            "eye_color": "brown",
            "birth_year": "72BBY",
            "gender": "male"
        },
        {
            "id": 52,
            "isMain": false,
            "name": "Ki-Adi-Mundi",
            "image_url": "https://m.media-amazon.com/images/M/MV5BMTMxNTEzMTkyM15BMl5BanBnXkFtZTcwNzU1NzI4NA@@._V1_SY1000_CR0,0,664,1000_AL_.jpg",
            "height": "198",
            "mass": "82",
            "hair_color": "white",
            "skin_color": "pale",
            "eye_color": "yellow",
            "birth_year": "92BBY",
            "gender": "male"
        },
        {
            "id": 53,
            "isMain": false,
            "name": "Kit Fisto",
            "image_url": "",
            "height": "196",
            "mass": "87",
            "hair_color": "none",
            "skin_color": "green",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 54,
            "isMain": false,
            "name": "Eeth Koth",
            "image_url": "",
            "height": "171",
            "mass": "unknown",
            "hair_color": "black",
            "skin_color": "brown",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 55,
            "isMain": false,
            "name": "Adi Gallia",
            "image_url": "https://pbs.twimg.com/profile_images/3534990862/da15c2223f00bef1ba8104a86acfa959.jpeg",
            "height": "184",
            "mass": "50",
            "hair_color": "none",
            "skin_color": "dark",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 56,
            "isMain": false,
            "name": "Saesee Tiin",
            "image_url": "https://m.media-amazon.com/images/M/MV5BMTg3MTM5ODA3OV5BMl5BanBnXkFtZTcwMTA2NzI4NA@@._V1_.jpg",
            "height": "188",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "pale",
            "eye_color": "orange",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 57,
            "isMain": false,
            "name": "Yarael Poof",
            "image_url": "",
            "height": "264",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "white",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 58,
            "isMain": false,
            "name": "Plo Koon",
            "image_url": "",
            "height": "188",
            "mass": "80",
            "hair_color": "none",
            "skin_color": "orange",
            "eye_color": "black",
            "birth_year": "22BBY",
            "gender": "male"
        },
        {
            "id": 59,
            "isMain": false,
            "name": "Mas Amedda",
            "height": "196",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "blue",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 60,
            "isMain": false,
            "name": "Gregar Typho",
            "height": "185",
            "mass": "85",
            "hair_color": "black",
            "skin_color": "dark",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 61,
            "isMain": false,
            "name": "Cordé",
            "height": "157",
            "mass": "unknown",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 62,
            "isMain": false,
            "name": "Cliegg Lars",
            "height": "183",
            "mass": "unknown",
            "hair_color": "brown",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "82BBY",
            "gender": "male"
        },
        {
            "id": 63,
            "isMain": false,
            "name": "Poggle the Lesser",
            "height": "183",
            "mass": "80",
            "hair_color": "none",
            "skin_color": "green",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 64,
            "isMain": false,
            "name": "Luminara Unduli",
            "image_url": "",
            "height": "170",
            "mass": "56.2",
            "hair_color": "black",
            "skin_color": "yellow",
            "eye_color": "blue",
            "birth_year": "58BBY",
            "gender": "female"
        },
        {
            "id": 65,
            "isMain": false,
            "name": "Barriss Offe",
            "image_url": "",
            "height": "166",
            "mass": "50",
            "hair_color": "black",
            "skin_color": "yellow",
            "eye_color": "blue",
            "birth_year": "40BBY",
            "gender": "female"
        },
        {
            "id": 66,
            "isMain": false,
            "name": "Dormé",
            "height": "165",
            "mass": "unknown",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 67,
            "isMain": true,
            "name": "Dooku",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/b/bd/Count_Dooku.png/330px-Count_Dooku.png",
            "height": "193",
            "mass": "80",
            "hair_color": "white",
            "skin_color": "fair",
            "eye_color": "brown",
            "birth_year": "102BBY",
            "gender": "male"
        },
        {
            "id": 68,
            "isMain": false,
            "name": "Bail Prestor Organa",
            "image_url": "https://m.media-amazon.com/images/M/MV5BMTMxNzI5OTY3NF5BMl5BanBnXkFtZTcwOTEwNTI4NA@@._V1_SY1000_CR0,0,651,1000_AL_.jpg",
            "height": "191",
            "mass": "unknown",
            "hair_color": "black",
            "skin_color": "tan",
            "eye_color": "brown",
            "birth_year": "67BBY",
            "gender": "male"
        },
        {
            "id": 69,
            "isMain": true,
            "name": "Jango Fett",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Jango_Fett.png/330px-Jango_Fett.png",
            "height": "183",
            "mass": "79",
            "hair_color": "black",
            "skin_color": "tan",
            "eye_color": "brown",
            "birth_year": "66BBY",
            "gender": "male"
        },
        {
            "id": 70,
            "isMain": false,
            "name": "Zam Wesell",
            "height": "168",
            "mass": "55",
            "hair_color": "blonde",
            "skin_color": "fair,green,yellow",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 71,
            "isMain": false,
            "name": "Dexter Jettster",
            "height": "198",
            "mass": "102",
            "hair_color": "none",
            "skin_color": "brown",
            "eye_color": "yellow",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 72,
            "isMain": false,
            "name": "Lama Su",
            "height": "229",
            "mass": "88",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 73,
            "isMain": false,
            "name": "Taun We",
            "height": "213",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 74,
            "isMain": false,
            "name": "Jocasta Nu",
            "height": "167",
            "mass": "unknown",
            "hair_color": "white",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 75,
            "isMain": false,
            "name": "R4-P17",
            "image_url": "",
            "height": "96",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "silver,red",
            "eye_color": "red,blue",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 76,
            "isMain": false,
            "name": "Wat Tambor",
            "image_url": "",
            "height": "193",
            "mass": "48",
            "hair_color": "none",
            "skin_color": "green,grey",
            "eye_color": "unknown",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 77,
            "isMain": false,
            "name": "San Hill",
            "height": "191",
            "mass": "unknown",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "gold",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 78,
            "isMain": false,
            "name": "Shaak Ti",
            "image_url": "",
            "height": "178",
            "mass": "57",
            "hair_color": "none",
            "skin_color": "red,blue,white",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 79,
            "isMain": true,
            "name": "General Grievous",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/GeneralGrievous.png/220px-GeneralGrievous.png",
            "height": "216",
            "mass": "159",
            "hair_color": "none",
            "skin_color": "brown,white",
            "eye_color": "green,yellow",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 80,
            "isMain": true,
            "name": "Tarfful",
            "height": "234",
            "mass": "136",
            "hair_color": "brown",
            "skin_color": "brown",
            "eye_color": "blue",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 81,
            "isMain": true,
            "name": "Raymus Antilles",
            "height": "188",
            "mass": "79",
            "hair_color": "brown",
            "skin_color": "light",
            "eye_color": "brown",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 82,
            "isMain": true,
            "name": "Sly Moore",
            "height": "178",
            "mass": "48",
            "hair_color": "none",
            "skin_color": "pale",
            "eye_color": "white",
            "birth_year": "unknown",
            "gender": "female"
        },
        {
            "id": 83,
            "isMain": true,
            "name": "Tion Medon",
            "height": "206",
            "mass": "80",
            "hair_color": "none",
            "skin_color": "grey",
            "eye_color": "black",
            "birth_year": "unknown",
            "gender": "male"
        },
        {
            "id": 84,
            "isMain": true,
            "name": "Kylo Ren",
            "image_url": "https://i.pinimg.com/236x/71/06/dc/7106dc5edb54d56cb15492e1772fbec5.jpg",
            "gender": "male"
        },
        {
            "id": 85,
            "isMain": true,
            "name": "Finn",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/2/2a/Finn-Force_Awakens_%282015%29.png",
            "gender": "male"
        },
        {
            "id": 86,
            "isMain": true,
            "name": "Poe Dameron",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/0/0b/Poe_Dameron-Force_Awakens_%282015%29.png",
            "gender": "male"
        },
        {
            "id": 87,
            "isMain": true,
            "name": "Maz Kanata",
            "image_url": "https://pm1.narvii.com/6314/59d6b3b421a2505ba5cb3717040d6f77fcd81a5a_128.jpg",
            "gender": "female"
        },
        {
            "id": 88,
            "isMain": true,
            "name": "Supreme Leader Snoke",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Snoke-The_Force_Awakens_%282015%29.png/330px-Snoke-The_Force_Awakens_%282015%29.png",
            "gender": "male"
        },
        {
            "id": 89,
            "isMain": false,
            "name": "General Hux",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/a/a4/General_Hux_Star_Wars.jpg",
            "gender": ""
        },
        {
            "id": 90,
            "isMain": true,
            "name": "BB-8",
            "image_url": "https://upload.wikimedia.org/wikipedia/en/3/39/BB-8%2C_Star_Wars_The_Force_Awakens.jpg",
            "gender": ""
        },
        {
            "id": 91,
            "isMain": true,
            "name": "Rose Tico",
            "image_url": "https://pm1.narvii.com/6521/766f393132d82c1087e11e1e505ccd464b5188da_128.jpg",
            "gender": "female"
        },
        {
            "id": 92,
            "isMain": true,
            "name": "Vice Almirante Holdo",
            "image_url": "https://vignette.wikia.nocookie.net/pt.starwars/images/a/a4/Holdo-Elle.png/revision/latest?cb=20171214221607",
            "gender": ""
        },
        {
            "id": 93,
            "isMain": false,
            "name": "Agen Kolar",
            "image_url": "https://pm1.narvii.com/6120/dbf3f6d657b1e3633440cb7615c54ae8bb37e094_128.jpg",
            "gender": ""
        },
        {
            "id": 94,
            "isMain": false,
            "name": "Shmi Skywalker",
            "image_url": "https://m.media-amazon.com/images/M/MV5BMjE0MDM2MzkzOF5BMl5BanBnXkFtZTcwNTkyMzA4NA@@._V1_SY1000_SX1500_AL_.jpg",
            "gender": ""
        },
        {
            "id": 95,
            "isMain": false,
            "name": "TC-14",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 96,
            "isMain": false,
            "name": "Almirante Firmus Piett",
            "image_url": "https://pm1.narvii.com/6441/87b607bb18ff99041bbd894aa9a56b6128170ea9_128.jpg",
            "gender": ""
        },
        
        {
            "id": 97,
            "isMain": false,
            "name": "Biggs",
            "image_url": "",
            "gender": ""
        },

        {
            "id": 98,
            "isMain": false,
            "name": "Coleman Trebor",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 99,
            "isMain": false,
            "name": "Dengar",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 100,
            "isMain": false,
            "name": "Depa Billaba",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 101,
            "isMain": false,
            "name": "E-3PO",
            "image_url": "",
            "gender": ""
        },

        {
            "id": 102,
            "isMain": false,
            "name": "Even Piell",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 103,
            "isMain": false,
            "name": "Galen Marek",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 104,
            "isMain": false,
            "name": "Gardulla",
            "image_url": "",
            "gender": ""
        },


        {
            "id": 105,
            "isMain": false,
            "name": "IG-88",
            "image_url": "",
            "gender": ""
        },
       
        {
            "id": 106,
            "isMain": false,
            "name": "Jocasta Nu",
            "image_url": "",
            "gender": ""
        },



        {
            "id": 107,
            "isMain": false,
            "name": "Nebit",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 108,
            "isMain": false,
            "name": "OOM-9",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 109,
            "isMain": false,
            "name": "Oppo Rancisis",
            "image_url": "",
            "gender": ""
        },

        {
            "id": 110,
            "isMain": false,
            "name": "Porg",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 111,
            "isMain": false,
            "name": "R2-Q5",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 112,
            "isMain": false,
            "name": "R4-F5",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 113,
            "isMain": false,
            "name": "R4-M9",
            "image_url": "",
            "gender": ""
        },

        {
            "id": 114,
            "isMain": false,
            "name": "Rune Hako",
            "image_url": "",
            "gender": ""
        },


        {
            "id": 115,
            "isMain": false,
            "name": "Shu Mai",
            "image_url": "",
            "gender": ""
        },
        {
            "id": 116,
            "isMain": false,
            "name": "Stass Allie",
            "image_url": "",
            "gender": ""
        },

        {
            "id": 117,
            "isMain": false,
            "name": "U-3PO",
            "image_url": "",
            "gender": ""
        }
    ]
}
 """

    }
}