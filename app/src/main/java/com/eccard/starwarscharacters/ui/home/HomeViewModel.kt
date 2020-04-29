package com.eccard.starwarscharacters.ui.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.testing.OpenForTesting
import com.eccard.starwarscharacters.util.SingleLiveEvent
import javax.inject.Inject

@OpenForTesting
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel(), Repository.SyncListener {

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


    private val _loading = SingleLiveEvent<Boolean>()

    val loading  : SingleLiveEvent<Boolean> = _loading

    init {
        loadFromApi()
    }

    // todo public only for testing
    fun loadFromApi(){
        _loading.postValue(true)
        repository.syncListener = this
        repository.syncWithApi()

    }

    override fun onSynced() {
        _loading.postValue(false)
        setQuery("")
    }

    override fun onCleared() {
        clear()
    }

    @VisibleForTesting
    fun clear(){
        repository.syncListener = null
    }

}
