package com.eccard.starwarscharacters.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class CharacterDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()
    private val repository = Mockito.mock(Repository::class.java)
    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        viewModel = CharacterDetailViewModel(repository)
    }

    @Test
    fun sendResultToUi() {
        val resultLive = MutableLiveData<String>()
        resultLive.value = "test"
        val charsId = 0

        `when`(repository.findFilmThatHasCharacter(charsId)).thenReturn(resultLive)
        val observer = mock<Observer<String>>()
        viewModel.filmOfCharacter.observeForever(observer)
        viewModel.setCharacterId(charsId)


        Mockito.verify(observer).onChanged(resultLive.value)
    }

    @Test
    fun getFilmOfCharacter() {
    }

    @Test
    fun setCharacterId() {
    }

    @Test
    fun getRepository() {
    }
}