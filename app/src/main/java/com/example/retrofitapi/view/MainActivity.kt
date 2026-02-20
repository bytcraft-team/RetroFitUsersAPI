package com.example.retrofitapi.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.compose.material3.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.Adapter.UserAdapter
import com.example.retrofitapi.R
import com.example.retrofitapi.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    lateinit var adapterUser : UserAdapter
    lateinit var recyclerId : RecyclerView
    lateinit var searchView : SearchView
    lateinit var btnFilter : Button
    lateinit var btnReset : Button

    val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerId = findViewById(R.id.recycler)
        recyclerId.layoutManager = LinearLayoutManager(this)
         searchView = findViewById(R.id.searchView)
         btnFilter = findViewById(R.id.btnFilter)
         btnReset = findViewById<Button>(R.id.btnReset)

        btnFilter.setOnClickListener {
            userViewModel.filterByNameStartsWithL()
        }

        btnReset.setOnClickListener {
            userViewModel.resetFilter()
        }



        adapterUser = UserAdapter(emptyList())
        recyclerId.adapter = adapterUser

       userViewModel.users.observe(this){ users ->
           adapterUser.updateData(users)

       }

        userViewModel.getUsers()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userViewModel.searchUsers(newText ?: "")
                return true
            }
        })


    }




}