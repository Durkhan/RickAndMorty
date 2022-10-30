package com.abb.rickandmorttask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abb.rickandmorttask.model.Character
import com.abb.rickandmorttask.model.CharactersInfo
import com.abb.rickandmorttask.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterListViewModel(val repository: Repository):ViewModel(){
    var liveDatalistofCharacter=MutableLiveData<Response<CharactersInfo>>()
    var listofFilteredItems=MutableLiveData<ArrayList<Character>>()
    var filterValue= MutableLiveData<Array<Int>>()
    var filterValues= MutableLiveData<Array<String>>()
    var isFilter=MutableLiveData<Boolean>()
    var isPaging=MutableLiveData<Boolean>()
    var name=MutableLiveData<String>()

    init {
        name.value=""
        listofFilteredItems.value= arrayListOf()
        filterValues.value= arrayOf("","","")
        filterValue.value= arrayOf(0,0,0)
        isFilter.value=false
        isPaging.value=true
    }

    fun getCharacters(page:Int){
        viewModelScope.launch {
            val characters=repository.getCharacters(page)
            liveDatalistofCharacter.value=characters;
            isFilter.value=false
        }
    }
    fun getBySpeciesAndStatusAndGenderAndName(name: String,species:String,status : String, gender: String, page:Int){
        viewModelScope.launch{
            isPaging.value=false
            isFilter.value = true
            val characters = repository.getCharactersbySpeciesAndStatusAndGenderByName(name,species,status, gender, page)
            liveDatalistofCharacter.value = characters

        }
    }



    }






