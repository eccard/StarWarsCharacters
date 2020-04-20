package com.eccard.starwarscharacters.ui.films

import androidx.lifecycle.ViewModel
import com.eccard.starwarscharacters.data.Repository
import javax.inject.Inject

class FilmsViewModel @Inject constructor(val repository: Repository) : ViewModel(){
    val films = repository.loadAllFilms()
}