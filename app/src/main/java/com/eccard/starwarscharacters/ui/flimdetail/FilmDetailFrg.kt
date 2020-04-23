package com.eccard.starwarscharacters.ui.flimdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.FilmDetailFrgBinding

class FilmDetailFrg: Fragment() {
    companion object {
        val TAG = FilmDetailFrg::class.simpleName

        const val FILM_KEY = "FILME_KEY"
        fun newInstance ( film : Film) : FilmDetailFrg {
            val frg = FilmDetailFrg()
            val args = Bundle()
            args.putParcelable(FILM_KEY,film)
            frg.arguments = args
            return frg
        }
    }

    lateinit var binding :FilmDetailFrgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.film_detail_frg,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val film = it.getParcelable<Film>(FILM_KEY)
            binding.film = film
        }
    }
}