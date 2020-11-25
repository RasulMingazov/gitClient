package com.example.gitclient.model.pojo

data class Repo(
    val branches: Int,
    val description: String,
    val fork: String,
    val name: String,
    val tags: Int,
    val watchers: Int,
    val language: String,
    val private: Boolean,
    val owner: owner
)