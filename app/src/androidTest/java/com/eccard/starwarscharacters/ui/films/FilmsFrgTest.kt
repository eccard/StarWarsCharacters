package com.eccard.starwarscharacters.ui.films

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.api.FilmRespose
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.*
import com.google.gson.Gson
import org.hamcrest.CoreMatchers.not
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmsFrgTest {

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: FilmsViewModel
    private val results = MutableLiveData<List<Film>>()
    private val navController = Mockito.mock(NavController::class.java)


    @Before
    fun setUp() {
        viewModel = Mockito.mock(FilmsViewModel::class.java)
        Mockito.`when`(viewModel.films).thenReturn(results)

        val scenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme) {
            FilmsFrg().apply {
                appExecutors = countingAppExecutors.appExecutors
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(),navController)
        }
    }

    @Test
    fun checkIfLoadingWhenInit() {
        Espresso.onView(ViewMatchers.withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfRemovesPgbWhenGotList() {
        val filmList = getFilmsList()
        results.postValue(filmList)

        Espresso.onView(ViewMatchers.withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun listFilms(){
        val filmList = getFilmsList()
        results.postValue(filmList)
        Espresso.onView(RecyclerViewMatcher(R.id.character_list).atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(filmList[0].name))))

        Espresso.onView(RecyclerViewMatcher(R.id.character_list).atPosition(1))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(filmList[1].name))))

    }

    @Test
    fun navigateToFilm(){
        val filmList = getFilmsList()
        results.postValue(filmList)

        Espresso.onView(ViewMatchers.withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withText(filmList[0].name)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            FilmsFrgDirections.showFilmDetail(filmList[0])
        )
    }

    private fun getFilmsList() : List<Film> {
        return Gson().fromJson(
            """
            {
              "total_count" : 7,
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
              }
            ]
        }
        """,FilmRespose::class.java).items
    }
}