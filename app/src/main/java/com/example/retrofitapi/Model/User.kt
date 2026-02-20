package com.example.retrofitapi.Model

data class User(
    var id : Int,
    val name : String,
    val username : String,
    val email : String,
    val address : Address,
    val phone : String,
    val website : String,
    val company: Company
)
