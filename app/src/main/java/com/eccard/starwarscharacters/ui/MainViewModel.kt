package com.eccard.starwarscharacters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eccard.starwarscharacters.data.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    val loading  : LiveData<Boolean> = repository.syncWithApi()


}