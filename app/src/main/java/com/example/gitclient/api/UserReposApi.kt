package com.example.gitclient.api

import com.example.gitclient.model.pojo.Repo
import retrofit2.http.GET
import retrofit2.http.Header

interface UserReposApi {
    @GET("/user/repos")
    suspend fun getAllUserRepos(
        @Header("Authorization") accessToken: String
    ): List<Repo>
}