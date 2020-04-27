package com.eccard.starwarscharacters.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(repository: Repository) : ViewModel(){

    private val _characterId = MutableLiveData<Int>()

    val filmOfCharacter : LiveData<String> = _characterId.switchMap { searchId ->
        repository.findFilmThatHasCharacter(searchId)
    }

    fun setCharacterId(characterId : Int) {
        _characterId.value = characterId
    }

}