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
    private val _urlToYouTube = MutableLiveData<String>()

    val characterInFilm : LiveData<List<CharacterAdapterPojo>> = _charactersIds.switchMap { filmIds ->
        if (filmIds==null){
          AbsentLiveData.create()
        } else {
            repository.findCharactersInFilm(filmIds)
        }
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


    val videoUrl : LiveData<String> = _urlToYouTube.switchMap { _urlToYouTube ->
        if (_urlToYouTube.isBlank()){
            AbsentLiveData.create()
        } else {
            repository.resolveVideoLink(_urlToYouTube)
        }
    }

    fun setYouTubeUrl(youTubeLink : String?){
        youTubeLink?.let {
            if (_urlToYouTube.value == youTubeLink){
                return
            }
            _urlToYouTube.value = youTubeLink
        }
    }
}