package com.eccard.starwarscharacters.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eccard.starwarscharacters.ui.MainViewModel
import com.eccard.starwarscharacters.ui.characterdetail.CharacterDetailViewModel
import com.eccard.starwarscharacters.ui.films.FilmsViewModel
import com.eccard.starwarscharacters.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterViewModel(viewModel: CharacterDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Singleton
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilmsViewModel::class)
    @Singleton
    abstract fun bindFilmViewModel(viewModel: FilmsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}