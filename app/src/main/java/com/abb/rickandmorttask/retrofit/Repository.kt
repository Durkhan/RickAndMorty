package com.abb.rickandmorttask.retrofit

import com.abb.rickandmorttask.model.CharactersInfo
import retrofit2.Response

class Repository {
    suspend fun getCharacters(page:Int):Response<CharactersInfo>{
        return RetrofitInstance.api.getCharacterList(page)
    }
    suspend fun getCharactersByName(name: String,species:String,status : String, gender: String, page:Int): Response<CharactersInfo>{
        return RetrofitInstance.api.getCharactersByName(name,species,status, gender, page)
    }

    suspend fun getCharactersbySpeciesAndStatusAndGender(species:String,status : String, gender: String, page:Int): Response<CharactersInfo>{
        return RetrofitInstance.api.getCharactersbyStatusAndGenderAndSpecies(species,status, gender, page)
    }

}