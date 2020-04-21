package com.eccard.starwarscharacters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.ActivityMainBinding
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

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        viewModel.loading.observe(this@MainActivity, Observer {
            loading ->
            loading?.let {
                binding.loading = loading
                if (!loading) {
                    navigateToHome()
                }
            }
        })

    }

    private fun navigateToHome() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_content, HomeFrg(), HomeFrg.TAG)
            .commit()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    fun navigateToDetailsFrg(charactter: Charactter){
        navigateToFrg(CharacterDetailFrg.newInstance(charactter),CharacterDetailFrg.TAG)
    }

    fun navigateToFilmDetailFrg(film: Film){
        navigateToFrg(FilmDetailFrg.newInstance(film),FilmDetailFrg.TAG)
    }

    fun navigateToFilmFrg(){
        navigateToFrg(FilmsFrg(),FilmsFrg.TAG)
    }

    private fun navigateToFrg(fragment : Fragment, tag : String?){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment,tag)
            .addToBackStack(tag)
            .commit()
    }


}
