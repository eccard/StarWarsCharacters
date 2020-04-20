package com.eccard.starwarscharacters.ui.films

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.FilmItemBinding
import com.eccard.starwarscharacters.databinding.FilmsFrgBinding
import com.eccard.starwarscharacters.databinding.HomeFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import com.eccard.starwarscharacters.ui.MainActivity
import com.eccard.starwarscharacters.ui.common.ItemOffsetDecoration
import com.eccard.starwarscharacters.ui.common.SimpleDividerItemDecoration
import com.eccard.starwarscharacters.ui.home.CharacterAdapter
import com.eccard.starwarscharacters.ui.home.HomeViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

class FilmsFrg: Fragment(), Injectable {
    companion object {
        val TAG = FilmsFrg::class.simpleName
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var binding : FilmsFrgBinding

    lateinit var adapter : FilmsAdapter

    val viewModel: FilmsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.films_frg,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    private fun initRecyclerView() {

        val posterWidth = binding.rvFilms.context.resources.getDimension(R.dimen.img_view_film_width)
        val display = activity!!.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val screenWidth = outMetrics.widthPixels.toFloat()
        val bestSpanCount = (screenWidth / posterWidth).roundToInt()
//        binding.rvFilms.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(binding.rvFilms.context,bestSpanCount)
        binding.rvFilms.layoutManager = layoutManager

        val rvAdapter = FilmsAdapter(appExecutors = appExecutors){
                //films -> (activity as MainActivity).navigateToDetailsFrg(character.charactter)
        }
        adapter = rvAdapter
        binding.rvFilms.adapter = adapter

        binding.rvFilms.addItemDecoration(ItemOffsetDecoration(binding.rvFilms.context,R.dimen.grid_spacing_small))

        viewModel.films.observe(viewLifecycleOwner, Observer<List<Film>?> {films ->
            adapter.submitList(films)
        })


    }

}