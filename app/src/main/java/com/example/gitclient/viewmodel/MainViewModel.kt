package com.example.gitclient.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitclient.model.pojo.AccessToken
import com.example.gitclient.model.pojo.NewRepo
import com.example.gitclient.model.pojo.Repo
import com.example.gitclient.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: Repository) : ViewModel() {

    val reposResponse: MutableLiveData<Response<List<Repo>>> = MutableLiveData()
    fun getAllUserRepos(tokenOfAccess: String) {
        viewModelScope.launch {
            val response = repository.getAllUserRepos(tokenOfAccess)
            reposResponse.value = response
        }
    }

    val authResponse: MutableLiveData<Response<AccessToken>> = MutableLiveData()
    fun  getAccessToken (clientId: String,
                         clientSecret: String,
                         code: String) {
        viewModelScope.launch {
            val response = repository.getAccessToken(clientId, clientSecret, code)
            authResponse.value = response
        }
    }

//    val newRepoResponse: MutableLiveData<Response<Repo>> = MutableLiveData()
//    fun createRepo(accessToken: String, newRepo: NewRepo) {
//        viewModelScope.launch {
//            val response = repository.createRepo(accessToken, newRepo)
//            newRepoResponse.value = response
//        }
//    }
}