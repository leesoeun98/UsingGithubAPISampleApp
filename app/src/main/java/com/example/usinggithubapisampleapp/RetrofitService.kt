package com.example.usinggithubapisampleapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private const val BASE_URL = "https://api.github.com"

        val retrofit = Retrofit.Builder()
            .baseUrl(this.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userAPI: GithubInterface = retrofit.create(GithubInterface::class.java)
    }
}