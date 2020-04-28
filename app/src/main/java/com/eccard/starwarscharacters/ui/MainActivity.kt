package com.eccard.starwarscharacters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.ui.characterdetail.CharacterDetailFrg
import com.eccard.starwarscharacters.ui.films.FilmsFrg
import com.eccard.starwarscharacters.ui.flimdetail.FilmDetailFrg
import com.eccard.starwarscharacters.ui.home.HomeFrg
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main )
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
