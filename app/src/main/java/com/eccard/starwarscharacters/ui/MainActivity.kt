package com.eccard.starwarscharacters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eccard.starwarscharacters.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main )
    }

    override fun androidInjector() = dispatchingAndroidInjector

}
