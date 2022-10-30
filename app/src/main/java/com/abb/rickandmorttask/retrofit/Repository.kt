package com.abb.rickandmorttask.retrofit

import com.abb.rickandmorttask.model.CharactersInfo
import retrofit2.Response

class Repository {
    suspend fun getCharacters(page:Int):Response<CharactersInfo>{
        return RetrofitInstance.api.getCharacterList(page)
    }
    suspend fun  getCharactersbySpeciesAndStatusAndGenderByName(name: String,species:String,status : String, gender: String, page:Int): Response<CharactersInfo>{
        return RetrofitInstance.api.getCharactersbySpeciesAndStatusAndGenderAndbyName(name,species,status, gender, page)
    }



}