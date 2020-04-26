package com.eccard.starwarscharacters.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel(){

    private val _query = MutableLiveData<String>()

    val query : LiveData<String> = _query

    val results : LiveData<List<CharacterAdapterPojo>> = _query.switchMap {
            query -> repository.findByNameOrFilm(query)
    }

    fun setQuery(originalInput: String) {
        val input = originalInput.trim()
        if (input == _query.value) {
            return
        }
        _query.value = input
    }


    private val _loading = LiveEvent<Boolean>()

    val loading  : LiveData<Boolean> = _loading

    init {
        loadFromApi()
    }

    private fun loadFromApi(){

        _loading.postValue(true)
        repository.syncListener = object : Repository.SyncListener {
            override fun onSynced() {
                _loading.postValue(false)
                setQuery("")
            }
        }
        repository.syncWithApi()

    }

    override fun onCleared() {
        repository.syncListener = null
    }
}
