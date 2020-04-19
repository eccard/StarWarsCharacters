package com.eccard.starwarscharacters.ui.home

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.databinding.HomeFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import com.eccard.starwarscharacters.ui.MainActivity
import com.eccard.starwarscharacters.ui.common.SimpleDividerItemDecoration
import javax.inject.Inject

class HomeFrg : Fragment(), Injectable {

    companion object {
        val TAG = "HomeFrg"//::class.simpleName
    }


    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var binding : HomeFrgBinding

    private lateinit var adapter: CharacterAdapter

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
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

        initRecyclerView()
        initSearchInputListener()

        homeViewModel.loadCharacter()
    }

    private fun initRecyclerView() {

        val rvAdapter = CharacterAdapter(appExecutors = appExecutors){
            character -> (activity as MainActivity).nativateToDetailsFrg(character.charactter)
        }

        android.R.layout.simple_list_item_1
        binding.query = homeViewModel.query
        binding.characterList.adapter = rvAdapter
        adapter = rvAdapter

        binding.characterList.addItemDecoration(SimpleDividerItemDecoration(binding.characterList.context))
        homeViewModel.results.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })

        homeViewModel.setQuery("")
//        binding.searchResult = searchViewModel.results
//        searchViewModel.results.observe(viewLifecycleOwner, Observer { result ->
//            adapter.submitList(result?.data)
//        })
//
//        searchViewModel.loadMoreStatus.observe(viewLifecycleOwner, Observer { loadingMore ->
//            if (loadingMore == null) {
//                binding.loadingMore = false
//            } else {
//                binding.loadingMore = loadingMore.isRunning
//                val error = loadingMore.errorMessageIfNotHandled
//                if (error != null) {
//                    Snackbar.make(binding.loadMoreBar, error, Snackbar.LENGTH_LONG).show()
//                }
//            }
//        })
    }


    // todo se for ficar abaixo com action up não precisa dessesky
    private fun initSearchInputListener() {

        binding.input.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                doSearch(binding.input,false)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.input.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        binding.input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
//            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.action == KeyEvent.ACTION_UP ) {
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
        homeViewModel.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}