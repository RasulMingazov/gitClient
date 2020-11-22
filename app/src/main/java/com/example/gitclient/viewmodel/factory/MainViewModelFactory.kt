package com.example.gitclient.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitclient.repository.Repository
import com.example.gitclient.viewmodel.MainViewModel

class MainViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         return MainViewModel(repository) as T
    }
}