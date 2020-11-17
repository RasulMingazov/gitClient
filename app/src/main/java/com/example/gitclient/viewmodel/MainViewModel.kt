package com.example.gitclient.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitclient.model.pojo.Repo
import com.example.gitclient.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<List<Repo>> = MutableLiveData()
    fun getAllUserRepos(tokenOfAccess: String) {
        viewModelScope.launch {
            val response = repository.getAllUserRepos(tokenOfAccess)
            myResponse.value = response
        }
    }
}