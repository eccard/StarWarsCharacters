package com.eccard.starwarscharacters.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class CharacterDetailViewModel @Inject constructor(repository: Repository) : ViewModel(){

    private val _characterId = MutableLiveData<Int>()

    val filmOfCharacter : LiveData<String> = _characterId.switchMap { searchId ->
        repository.findFilmThatHasCharacter(searchId)
    }

    fun setCharacterId(characterId : Int) {
        _characterId.value = characterId
    }

}