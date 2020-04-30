package com.eccard.starwarscharacters.ui.characterdetail

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.util.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class CharacterDetailFrgTest {

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: CharacterDetailViewModel
    private val results = MutableLiveData<String>()
    private val characterAnakin = getCharacterResponse().items[0]

    @Before
    fun setUp() {
        viewModel = mock(CharacterDetailViewModel::class.java)
        Mockito.`when`(viewModel.filmOfCharacter).thenReturn(results)

        val scenario = launchFragmentInContainer(
            CharacterDetailFrgArgs(characterAnakin).toBundle(),
            themeResId = R.style.AppTheme) {
            CharacterDetailFrg().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)
    }

    @Test
    fun checkDisplayInfos(){


        Espresso.onView(ViewMatchers.withId(R.id.tv_character_name))
            .check(ViewAssertions.matches(ViewMatchers.withText(characterAnakin.name)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_character_main))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                if (characterAnakin.isMain)
                    getString(R.string.main_character)
                else
                    getString((R.string.support_character)))))

        Espresso.onView(ViewMatchers.withId(R.id.tv_character_gender))
            .check(ViewAssertions.matches(ViewMatchers.withText(characterAnakin.gender)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_character_height))
            .check(ViewAssertions.matches(ViewMatchers.withText(characterAnakin.height)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_character_mass))
            .check(ViewAssertions.matches(ViewMatchers.withText(characterAnakin.mass)))

        val appearances = getFilmResponse().items[0].completeName
        results.postValue(appearances)

        Espresso.onView(ViewMatchers.withId(R.id.tv_character_appearances_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(appearances.replace("-","\n").replace(",","\n\n"))))

    }

    private fun getString(@StringRes id: Int, vararg args: Any): String {
        return ApplicationProvider.getApplicationContext<Context>().getString(id, *args)
    }
}