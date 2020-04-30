package com.eccard.starwarscharacters.ui.flimdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.util.getCharacterResponse
import com.eccard.starwarscharacters.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never

@RunWith(JUnit4::class)
class FilmDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private val repository = Mockito.mock(Repository::class.java)
    private lateinit var viewModel: FilmDetailViewModel

    @Before
    fun setUp() {
        viewModel = FilmDetailViewModel(repository)
    }

    @Test
    fun sendNoResultToUi() {
        val resultLive = MutableLiveData<List<CharacterAdapterPojo>>()
        val charsIds = listOf(0, 5)

        `when`(repository.findCharactersInFilm(charsIds)).thenReturn(resultLive)

        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        viewModel.characterInFilm.observeForever(observer)
        viewModel.setCharactersIds(charsIds)
        Mockito.verify(observer, never()).onChanged(any())


        resultLive.value = getCharacterResponse().items.map { CharacterAdapterPojo(it, null) }
        Mockito.verify(observer).onChanged(resultLive.value)
    }

    @Test
    fun sendResultToUi() {
        val resultLive = MutableLiveData<List<CharacterAdapterPojo>>()
        resultLive.value = getCharacterResponse().items.map { CharacterAdapterPojo(it, null) }
        val charsIds = listOf(0, 5)

        `when`(repository.findCharactersInFilm(charsIds)).thenReturn(resultLive)

        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        viewModel.characterInFilm.observeForever(observer)
        viewModel.setCharactersIds(charsIds)


        Mockito.verify(observer).onChanged(resultLive.value)
    }

    @Test
    fun sendSetIdWithNulll() {
        val charsIds = null

        val observer = mock<Observer<List<CharacterAdapterPojo>>>()
        viewModel.characterInFilm.observeForever(observer)
        viewModel.setCharactersIds(charsIds)

        Mockito.verify(observer,never()).onChanged(any())
    }

}