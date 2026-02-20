package com.example.retrofitapi

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapi.viewmodel.UserViewModel

class ActivityDetails : AppCompatActivity() {

    private lateinit var txtDetails: TextView
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        txtDetails = findViewById(R.id.txtDetails)

        val id = intent.getIntExtra("id", -1)

        if (id != -1) {
            userViewModel.getUserById(id)

            userViewModel.user.observe(this) { user ->
                if (user != null) {
                    txtDetails.text = """
                        ID: ${user.id}
                        Name: ${user.name}
                        Username: ${user.username}
                        Email: ${user.email}
                        address => rue : ${user.address.street}
                        addres => ville : ${user.address.city}
                        coordonneesGeo : ${user.address.geo.lat} ,${user.address.geo.lng}
                        Phone: ${user.phone}
                        Website: ${user.website}
                        entreprise : ${user.company.name}
                    """.trimIndent()
                }
            }

        } else {
            Toast.makeText(this, "ID invalide", Toast.LENGTH_SHORT).show()
        }
    }
}