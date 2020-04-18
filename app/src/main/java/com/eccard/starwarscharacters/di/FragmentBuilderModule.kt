package com.eccard.starwarscharacters.di

import com.eccard.starwarscharacters.ui.home.HomeFrg
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFrg(): HomeFrg
}