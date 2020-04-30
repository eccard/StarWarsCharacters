package com.eccard.starwarscharacters.ui.films

import androidx.lifecycle.ViewModel
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class FilmsViewModel @Inject constructor(repository: Repository) : ViewModel(){
    val films = repository.loadAllFilms()
}