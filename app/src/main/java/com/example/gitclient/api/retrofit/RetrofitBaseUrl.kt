package com.example.gitclient.api.retrofit

import com.example.gitclient.api.GithubApi
import com.example.gitclient.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBaseUrl {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: GithubApi by lazy {
        retrofit.create(GithubApi::class.java)
    }
}