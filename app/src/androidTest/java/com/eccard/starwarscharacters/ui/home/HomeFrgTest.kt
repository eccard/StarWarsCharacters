package com.eccard.starwarscharacters.ui.home

import android.view.KeyEvent
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.util.*
import com.eccard.starwarscharacters.util.com.eccard.starwarscharacters.util.getCharacterResponse
import org.hamcrest.CoreMatchers.not
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class HomeFrgTest {

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: HomeViewModel
    private val results = MutableLiveData<List<CharacterAdapterPojo>>()
    private val loading = SingleLiveEvent<Boolean>()

    @Before
    fun setUp() {
        viewModel = mock(HomeViewModel::class.java)
        loading.postValue(true)
        Mockito.doReturn(loading).`when`(viewModel).loading
        Mockito.`when`(viewModel.results).thenReturn(results)

        val scenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme) {
            HomeFrg().apply {
                appExecutors = countingAppExecutors.appExecutors
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)

    }

    @Test
    fun checkIfLoadingWhenInit() {
        Espresso.onView(withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfRemoveLoadingBarAfterSync() {
        Espresso.onView(withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        loading.postValue(false)

        Espresso.onView(withId(R.id.loading_pbg))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))



    }

    @Test
    fun loadCharacters() {
        loading.postValue(false)
        results.postValue(getCharacterResponse().items.map { CharacterAdapterPojo(it,null) })
        Espresso.onView(RecyclerViewMatcher(R.id.character_list).atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(getCharacterResponse().items[0].name))))
    }

    @Test
    fun searchCharacter(){
        loading.postValue(false)
        results.postValue(getCharacterResponse().items.map { CharacterAdapterPojo(it,null) })
        Espresso.onView(RecyclerViewMatcher(R.id.character_list).atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(getCharacterResponse().items[0].name))))

        val query = "anakin"
        Espresso.onView(withId(R.id.input)).perform(
            ViewActions.typeText(query),
            ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)
        )

        verify(viewModel).setQuery(query)

    }


}