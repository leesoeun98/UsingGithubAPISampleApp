package com.example.usinggithubapisampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usinggithubapisampleapp.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class UserAdapter(var userList: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private lateinit var itemBinding: ItemUserBinding

    inner class UserViewHolder(private val itemBinding: ItemUserBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: User){

            if (data.thumbnail != "") {
                Picasso.get()
                    .load(data.thumbnail)
                    .centerCrop()
                    .fit()
                    .into(itemBinding.ivThumbnail)
            }
            itemBinding.tvName.text = data.userName
            itemBinding.tvUrl.text = data.userUrl
        }

    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - viewHolder에 layout inflate 하는 함수 (ViewBinding 사용)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        itemBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - viewHolder에 각 view를 bind하는 함수
    // UserViewHolder내에 bind함수 정의했으므로, 각 userList[position]인 item data랑 view를 bind하면 됨
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - 보통 Todos.size를 return, RecyclerView내의 item 개수 return하는 함수
    override fun getItemCount(): Int {
        return userList.size
    }
}