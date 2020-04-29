package com.eccard.starwarscharacters.ui.flimdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.util.*
import com.eccard.starwarscharacters.util.getCharacterResponse
import com.eccard.starwarscharacters.util.getFilmResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmDetailFrgTest {

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: FilmDetailViewModel
    private val results = MutableLiveData<List<CharacterAdapterPojo>>()
    private val filmNewHope = getFilmResponse().items[0]
    private val navController = Mockito.mock(NavController::class.java)

    @Before
    fun setUp() {
        viewModel = Mockito.mock(FilmDetailViewModel::class.java)
        Mockito.`when`(viewModel.characterInFilm).thenReturn(results)

        val scenario = launchFragmentInContainer(
            FilmDetailFrgArgs(filmNewHope).toBundle(),
            themeResId = R.style.AppTheme) {
            FilmDetailFrg().apply {
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
    fun showFilmDetails(){

        Espresso.onView(ViewMatchers.withId(R.id.tv_film_complete_name))
            .check(ViewAssertions.matches(ViewMatchers.withText(filmNewHope.completeName)))


        Espresso.onView(ViewMatchers.withId(R.id.tv_film_complete_name))
            .check(ViewAssertions.matches(ViewMatchers.withText(filmNewHope.completeName)))


        Espresso.onView(ViewMatchers.withId(R.id.tv_film_launch_date))
            .check(ViewAssertions.matches(ViewMatchers.withText(filmNewHope.launchDate)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_film_detail_director))
            .check(ViewAssertions.matches(ViewMatchers.withText(filmNewHope.director)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_film_deails_synopsis))
            .check(ViewAssertions.matches(ViewMatchers.withText(filmNewHope.synopsis)))


        val charsList = getCharacterResponse().items.map { CharacterAdapterPojo(it,null) }
        results.postValue(charsList)
        checkIfCharactersAreInList(charsList)

    }

    @Test
    fun navigateToCharacter(){
        val charsList = getCharacterResponse().items.map { CharacterAdapterPojo(it,null) }
        results.postValue(charsList)

        Espresso.onView(ViewMatchers.withText(charsList[0].charactter.name)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            FilmDetailFrgDirections.showCharacterDetail(charsList[0].charactter)
        )
    }


    private fun checkIfCharactersAreInList(charsList : List<CharacterAdapterPojo>){
        for (idx in charsList.indices){
            Espresso.onView(RecyclerViewMatcher(R.id.rv_characters_in_film).atPosition(idx))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(charsList[idx].charactter.name))))

        }

    }
}