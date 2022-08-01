package com.example.usinggithubapisampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(getUser("leesoeun98"))
        getUserList(20)
    }

    private fun getUser(userName: String){
        RetrofitService.userAPI.getUser(username = userName).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            Log.d("GET_USER", response.body().toString())
                        }
                    } else { // response.code == 400 or 300
                        Log.d("CLIENT_ERR", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("RETROFIT_ERR", t.message.toString())
                }
            }
        )
    }

    private fun getUserList(perPage: Int){
        RetrofitService.userAPI.getUserList(per_page = perPage).enqueue(
            object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            Log.d("GET_USER_LIST", response.body().toString())
                        }
                    } else { // response.code == 400 or 300
                        Log.d("CLIENT_ERR", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("RETROFIT_ERR", t.message.toString())
                }
            }
        )
    }
}
