package com.eccard.starwarscharacters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eccard.starwarscharacters.data.Repository
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private val _loading = LiveEvent<Boolean>()

    val loading  : LiveData<Boolean> = _loading

    init {
        loadFromApi()
    }

    fun loadFromApi(){

        _loading.postValue(true)
        repository.syncListener = object : Repository.SyncListener {
            override fun onSynced() {
                _loading.postValue(false)
            }
        }
        repository.syncWithApi()

    }

    override fun onCleared() {
        repository.syncListener = null
    }
}
