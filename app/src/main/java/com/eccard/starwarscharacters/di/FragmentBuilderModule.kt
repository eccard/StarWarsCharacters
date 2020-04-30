package com.eccard.starwarscharacters.di

import com.eccard.starwarscharacters.ui.characterdetail.CharacterDetailFrg
import com.eccard.starwarscharacters.ui.films.FilmsFrg
import com.eccard.starwarscharacters.ui.flimdetail.FilmDetailFrg
import com.eccard.starwarscharacters.ui.home.HomeFrg
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFrg(): HomeFrg

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsFrg(): CharacterDetailFrg

    @ContributesAndroidInjector
    abstract fun contributeFilmFrg(): FilmsFrg

    @ContributesAndroidInjector
    abstract fun contributeFilmDetailsFrg(): FilmDetailFrg
}