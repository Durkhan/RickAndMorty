package com.abb.rickandmorttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abb.rickandmorttask.retrofit.Repository

class CharactersViewModelFactory (private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterListViewModel(repository) as T
    }
}