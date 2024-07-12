package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registerActivity2 : AppCompatActivity() {
    private lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register2)
        db = Database(this, "myhealth.db", null, 1)

        val usernameEditText: EditText = findViewById(R.id.editTexApptName)
        val passwordEditText: EditText = findViewById(R.id.editTextAppcontact)
        val confirmPasswordEditText: EditText = findViewById(R.id.editTextAppFees)
        val emailEditText: EditText = findViewById(R.id.editTextAppAddress)
        val phoneEditText: EditText = findViewById(R.id.editTextPhone)
        val registerButton: Button = findViewById(R.id.buttonBookApp)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                db.register(username,email,password)
                // Handle the registration logic here (e.g., save user info to the database, etc.)
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
                // Optionally, navigate to another activity
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}