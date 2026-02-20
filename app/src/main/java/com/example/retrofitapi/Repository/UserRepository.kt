package com.example.retrofitapi.Repository

import com.example.retrofitapi.Model.User
import com.example.retrofitapi.Service.RetrofitUser
import retrofit2.Response

class UserRepository {
    private val apiUser = RetrofitUser.objet

    suspend fun getUsers() : Response<List<User>>{
        return apiUser.getUsers()

    }

    suspend fun getUserById(id: Int): Response<User> {
        return apiUser.getUserById(id)
    }

}