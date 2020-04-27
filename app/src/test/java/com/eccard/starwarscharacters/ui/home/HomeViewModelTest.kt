package com.eccard.starwarscharacters.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()
    private val repository = mock(Repository::class.java)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun TestQuery() {
        val query = "ana"
        val result = mock<Observer<List<CharacterAdapterPojo>>>()
        viewModel.results.observeForever(result)
        viewModel.setQuery(query)
        verify(repository).findByNameOrFilm(query)
    }


    @Test
    fun syncWithApi() {
        `when`(repository.syncWithApi()).thenAnswer {viewModel.onSynced()}
        viewModel.loadFromApi()
        val data = mock<Observer<Boolean>>()
        viewModel.loading.observeForever(data)
        verify(data).onChanged(false)
    }

    @Test
    fun onCleared() {
        viewModel.clear()
        verify(repository).syncListener = null
    }
}