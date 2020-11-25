package com.example.gitclient.model.pojo

import com.google.gson.annotations.SerializedName

data class NewRepo(
       val name: String,
        @SerializedName("private") val isPrivate: Boolean = false,
       val description: String
)