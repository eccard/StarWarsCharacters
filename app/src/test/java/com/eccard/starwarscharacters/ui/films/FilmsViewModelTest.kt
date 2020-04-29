package com.eccard.starwarscharacters.ui.films

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.util.getFilmResponse
import com.eccard.starwarscharacters.util.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class FilmsViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()
    private val repository = Mockito.mock(Repository::class.java)
    private lateinit var viewModel: FilmsViewModel


    @Test
    fun getFilms() {
        val resultLive = MutableLiveData<List<Film>>()
        resultLive.value = getFilmResponse().items
        Mockito.`when`(repository.loadAllFilms()).thenReturn(resultLive)
        viewModel = FilmsViewModel(repository)

        val observer = mock<Observer<List<Film>>>()
        viewModel.films.observeForever(observer)
        Mockito.verify(observer).onChanged(resultLive.value)
    }

}