package com.example.gitclient.repository

import com.example.gitclient.api.retrofit.RetrofitApiUrl
import com.example.gitclient.api.retrofit.RetrofitBaseUrl
import com.example.gitclient.model.pojo.AccessToken
import com.example.gitclient.model.pojo.NewRepo
import com.example.gitclient.model.pojo.Repo
import retrofit2.Response

class Repository {
    suspend  fun getAllUserRepos(tokenOfAccess: String): Response<List<Repo>> {
        return RetrofitApiUrl.api.getAllUserRepos("token $tokenOfAccess")
    }
    suspend fun getAccessToken(clientId: String,
                               clientSecret: String,
                               code: String): Response<AccessToken> {
        return RetrofitBaseUrl.api.getAccessToken(clientId,clientSecret,code)
    }
//    suspend fun createRepo(accessToken: String, newRepo: NewRepo): Response<Repo> {
//        return RetrofitApiUrl.api.createRepo(accessToken, newRepo)
//    }
}