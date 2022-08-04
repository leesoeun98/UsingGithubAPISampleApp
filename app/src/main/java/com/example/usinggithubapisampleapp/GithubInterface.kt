package com.example.usinggithubapisampleapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubInterface {
    @GET("users")
    fun getUserList(@Header("accept") accept: String = "application/vnd.github+json",
                    @Query("per_page")per_page: Int): Call<List<User>>

    @GET("users/{username}")
    fun getUser(@Header("accept")accept: String = "application/vnd.github+json",
                @Path("username")username: String): Call<User>
}