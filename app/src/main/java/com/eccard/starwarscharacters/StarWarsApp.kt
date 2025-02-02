package com.eccard.starwarscharacters

import android.app.Application
import com.eccard.starwarscharacters.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import timber.log.Timber
import javax.inject.Inject

class StarWarsApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
        Realm.init(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector

}