package com.eccard.starwarscharacters.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.databinding.CharacterDetailFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import com.eccard.starwarscharacters.util.autoCleared
import javax.inject.Inject

class CharacterDetailFrg : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private var binding by autoCleared<CharacterDetailFrgBinding>()

    private val viewModel: CharacterDetailViewModel by viewModels {
        viewModelFactory
    }

    private val params by navArgs<CharacterDetailFrgArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_detail_frg,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.character = params.character
        viewModel.setCharacterId(params.character.id)

        viewModel.filmOfCharacter.observe(viewLifecycleOwner, Observer { filmsOfCharacter ->
            binding.filmsOfCharacter = filmsOfCharacter.replace("-","\n").replace(",","\n\n")
        })
    }
}
