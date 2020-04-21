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
        if ( savedInstanceState == null){
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_content, HomeFrg(), HomeFrg.TAG)
            .commit()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    fun navigateToDetailsFrg(charactter: Charactter) {
        navigateToFrg(CharacterDetailFrg.newInstance(charactter), CharacterDetailFrg.TAG)
    }

    fun navigateToFilmDetailFrg(film: Film) {
        navigateToFrg(FilmDetailFrg.newInstance(film), FilmDetailFrg.TAG)
    }

    fun navigateToFilmFrg() {
        navigateToFrg(FilmsFrg(), FilmsFrg.TAG)
    }

    private fun navigateToFrg(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}
