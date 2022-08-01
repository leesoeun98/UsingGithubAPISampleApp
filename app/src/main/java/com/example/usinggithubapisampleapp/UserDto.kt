package com.example.usinggithubapisampleapp

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("bio")
    val bio: String,
    @SerializedName("url")
    val userUrl: String,
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val thumbnail: String
)
