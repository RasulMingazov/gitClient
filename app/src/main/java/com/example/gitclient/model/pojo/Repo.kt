package com.example.gitclient.model.pojo

data class Repo(
    val branches: Int,
    val fork: String,
    val name: String,
    val tags: Int
)