package com.example.myhealth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") ?: ""

        Toast.makeText(applicationContext, "Welcome $username", Toast.LENGTH_SHORT).show()

        val exit = findViewById<View>(R.id.cardExit)
        exit.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
//find doctor
        val findDoctor = findViewById<CardView>(R.id.cardFindDoctor)
        findDoctor.setOnClickListener {
            startActivity(Intent(this@MainActivity, FindDocActivity::class.java))
        }

//appointment Details
        val orderDetails = findViewById<CardView>(R.id.cardOrderDetails)
        orderDetails.setOnClickListener {
            startActivity(Intent(this@MainActivity, BookingAppActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}