package com.example.gitclient.api

import com.example.gitclient.model.pojo.AccessToken
import com.example.gitclient.model.pojo.NewRepo
import com.example.gitclient.model.pojo.Repo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {
    @GET("/user/repos")
    suspend fun getAllUserRepos(
        @Header("Authorization") accessToken: String
    ): Response<List<Repo>>

    @DELETE("/repos/{owner}/{repo}")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun deleteRepo(
        @Header("Authorization") accessToken: String,
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ) : Call<Response<Void>>


    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Response<AccessToken>

    @POST("/user/repos")
    @Headers("Accept: application/vnd.github.v3+json")
    fun createRepo(
            @Header("Authorization") accessToken: String,
            @Body repo: NewRepo
    ): Call<Repo>
}

