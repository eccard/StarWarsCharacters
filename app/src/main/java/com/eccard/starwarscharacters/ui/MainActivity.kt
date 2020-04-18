package com.eccard.starwarscharacters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.databinding.ActivityMainBinding
import com.eccard.starwarscharacters.databinding.HomeFrgBinding
import com.eccard.starwarscharacters.ui.home.HomeFrg
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_content, HomeFrg())
            .commit()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
