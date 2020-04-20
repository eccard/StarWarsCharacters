package com.eccard.starwarscharacters.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.databinding.CharacterDetailFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import javax.inject.Inject

class CharacterDetailFrg : Fragment(), Injectable {

    companion object {
        val TAG = CharacterDetailFrg::class.simpleName

        const val CHARACTER_KEY = "characterKey"
        fun newInstance ( character : Charactter) : CharacterDetailFrg  {
            val frg = CharacterDetailFrg()
            val args = Bundle()
            args.putParcelable(CHARACTER_KEY,character)
            frg.arguments = args
            return frg
        }
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var binding : CharacterDetailFrgBinding

    val viewModel: CharacterDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_detail_frg,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val character = it.getParcelable<Charactter>(CHARACTER_KEY)
            binding.character = character
        }
    }
}