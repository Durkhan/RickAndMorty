package com.abb.rickandmorttask.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private var base_url:String="https://rickandmortyapi.com/api/"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

     val api:Api by lazy {
         retrofit.create(Api::class.java)
     }
    }
}