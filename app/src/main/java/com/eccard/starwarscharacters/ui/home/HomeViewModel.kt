package com.eccard.starwarscharacters.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.eccard.starwarscharacters.data.Repository
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.util.AbsentLiveData
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel(){

//    val results = MutableLiveData<List<Charactter>>()

    private val _query = MutableLiveData<String>()

    val query : LiveData<String> = _query


//    fun getResults() : LiveData<List<Charactter>> {
//       return _query.switchMap {
//        search -> if (search.isBlank()){
//            AbsentLiveData.create()
//        } else {
//            repository.asLiveData()
//        }
//        }
//    }

//    val results : LiveData<List<Charactter>> = _query.switchMap {
//        search -> if (search.isBlank()){
//            repository.loadAllCharacttersFromDb()
//            repository.asLiveData()
//        } else {
//            AbsentLiveData.create()
//        }
//    }

//    fun tt(){
//        _query.switchMap {  }
//    }

    val results : LiveData<List<CharacterAdapterPojo>> = _query.switchMap {
            query -> repository.findByNameOrFilm(query)
    }
//    val results : LiveData<List<Charactter>> = repository.asLiveData()


    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
//        nextPageHandler.reset()
        _query.value = input
    }

    fun loadCharacter(){
        repository.loadCharactersFromApi()
    }

    fun loadFromApiWithName(name : String){
//        repository.
    }

}
