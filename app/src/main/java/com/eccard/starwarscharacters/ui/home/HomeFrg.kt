package com.eccard.starwarscharacters.ui.home

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.databinding.HomeFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import com.eccard.starwarscharacters.util.autoCleared
import com.eccard.starwarscharacters.util.common.SimpleDividerItemDecoration
import javax.inject.Inject

class HomeFrg : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private var binding by autoCleared<HomeFrgBinding>()

    private var adapter by autoCleared<CharacterAdapter>()

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_frg,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        initSearchInputListener()
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                binding.loading = loading
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_films -> {
                dismissKeyboard(binding.input.windowToken)
                findNavController().navigate(HomeFrgDirections.showFilms())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {

        val rvAdapter = CharacterAdapter(appExecutors = appExecutors){
            findNavController().navigate(HomeFrgDirections.showCharacterDetail(it.charactter))
        }

        binding.query = viewModel.query
        binding.characterList.adapter = rvAdapter
        adapter = rvAdapter

        binding.characterList.addItemDecoration(SimpleDividerItemDecoration(binding.characterList.context))
        binding.searchResult = viewModel.results
        viewModel.results.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
    }


    private fun initSearchInputListener() {
        binding.input.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        binding.input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }

    }


    private fun doSearch(v: View, isToDissmissKeyboard :Boolean = true) {
        val query = binding.input.text.toString()

        if (isToDissmissKeyboard){
            // Dismiss keyboard
            dismissKeyboard(v.windowToken)
        }
        viewModel.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}