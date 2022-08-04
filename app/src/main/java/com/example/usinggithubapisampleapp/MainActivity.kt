package com.example.usinggithubapisampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usinggithubapisampleapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. retrofit으로 userlist 불러온 후, Adapter 생성 및 설정
        getUserList(20)
    }

    private fun setAdapter(userList: List<User>){
        adapter = UserAdapter(userList)
        // 1. onBindViewHolder 최적화
        adapter.setHasStableIds(true)
        // 2. adapter 설정
        binding.rvUsers.adapter = adapter
        // 3. RecyclerView에 LayoutManager 설정하기
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
    }

    // 특정 user 데이터를 갖고오는 함수
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

    // userList 데이터를 갖고오는 함수
    private fun getUserList(perPage: Int){
        RetrofitService.userAPI.getUserList(per_page = perPage).enqueue(
            object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            val body= response.body()
                            Log.d("SUCCESS", body.toString())
                            body?.let{
                                setAdapter(it)
                            }
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
