package com.example.retrofitapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapi.Model.User
import com.example.retrofitapi.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _allUsers = mutableListOf<User>()

    private val repository = UserRepository()

    // LiveData لجميع المستخدمين (RecyclerView)
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    // LiveData لمستخدم واحد (DetailActivity)
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    // ----------------------------------------
    // جلب جميع المستخدمين
    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = repository.getUsers()

                if (response.isSuccessful && response.body() != null) {

                    val list = response.body()!!

                    _allUsers.clear()
                    _allUsers.addAll(list)

                    _users.value = list

                } else {
                    _users.value = emptyList()
                }

            } catch (e: Exception) {
                _users.value = emptyList()
            }
        }
    }

    fun searchUsers(query: String) {
        if (query.isEmpty()) {
            _users.value = _allUsers
        } else {
            _users.value = _allUsers.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun filterByNameStartsWithL() {
        _users.value = _allUsers.filter { it.name.startsWith("C", ignoreCase = true) }
    }

    fun resetFilter() {
        _users.value = _allUsers
    }

    // ----------------------------------------
    fun getUserById(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getUserById(id)
                if (response.isSuccessful && response.body() != null) {
                    _user.value = response.body()
                } else {
                    _user.value = null
                }
            } catch (e: Exception) {
                _user.value = null
            }
        }
    }
}