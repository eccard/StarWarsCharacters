package com.eccard.starwarscharacters.ui.flimdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.util.AbsentLiveData
import javax.inject.Inject

class FilmDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private val _charactersIds = MutableLiveData<List<Int>>()

    val characterInFilm : LiveData<List<CharacterAdapterPojo>> = _charactersIds.switchMap { filmIds ->
        repository.findCharactersInFilm(filmIds)
    }

    fun setCharactersIds(charactersIds :List<Int>?){
        charactersIds?.let {
            _charactersIds.value?.let{_ids ->
                if (!_ids.containsAll(charactersIds)){
                    _charactersIds.value = charactersIds
                }
            } ?: run {
                _charactersIds.value = charactersIds
            }
        }

    }

}