package com.abb.rickandmorttask.retrofit

import com.abb.rickandmorttask.model.CharactersInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("character")
    suspend fun getCharacterList(@Query("page") page : Int): Response<CharactersInfo>

    @GET("character")
    suspend fun getCharactersByName(@Query("name") name : String,
                                    @Query("species") species : String,
                                    @Query("status") status : String,
                                    @Query("gender") gender : String,
                                    @Query("page") page : Int):Response<CharactersInfo>

    @GET("character")
    suspend fun getCharactersbyStatusAndGenderAndSpecies(@Query("species") species : String,
                                                @Query("status") status : String,
                                                @Query("gender") gender : String,
                                                @Query("page") page : Int):Response<CharactersInfo>

}