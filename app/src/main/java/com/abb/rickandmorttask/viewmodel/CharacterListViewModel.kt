package com.abb.rickandmorttask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abb.rickandmorttask.model.CharactersInfo
import com.abb.rickandmorttask.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterListViewModel(val repository: Repository):ViewModel(){
    var liveDatalistofCharacter=MutableLiveData<Response<CharactersInfo>>()
    var filterValue= MutableLiveData<Array<Int>>()
    var filterValues= MutableLiveData<Array<String>>()
    var isFilter=MutableLiveData<Boolean>()

    init {
        filterValues.value= arrayOf("","","")
        filterValue.value= arrayOf(0,0,0)
        isFilter.value=false
    }

    fun getCharacters(page:Int){
        viewModelScope.launch {
            val characters=repository.getCharacters(page)
            liveDatalistofCharacter.value=characters;
            isFilter.value=false
        }
    }
    fun getByName(name: String,species:String,status : String, gender: String, page:Int){
        viewModelScope.launch{
            val characters = repository.getCharactersByName(name,species,status, gender, page)
            liveDatalistofCharacter.value = characters
            isFilter.value = true
        }
    }
    fun getBySpeciesAndStatusAndGender(species:String,status : String, gender: String, page:Int){
        viewModelScope.launch{
            val characters = repository.getCharactersbySpeciesAndStatusAndGender(species,status, gender, page)
            liveDatalistofCharacter.value = characters
            isFilter.value = true
        }
    }

    }






