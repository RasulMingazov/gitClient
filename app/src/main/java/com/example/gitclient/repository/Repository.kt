package com.example.gitclient.repository

import com.example.gitclient.api.RetrofitInstanceRepos
import com.example.gitclient.model.pojo.Repo

class Repository {
    suspend  fun getAllUserRepos(tokenOfAccess: String): List<Repo> {
        return RetrofitInstanceRepos.api.getAllUserRepos("token $tokenOfAccess")
    }
}