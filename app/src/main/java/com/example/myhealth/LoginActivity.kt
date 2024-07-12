package com.example.myhealth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

private lateinit var db:Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        db=Database(this,"myhealth.db",null,1)

        var usernameEditText=findViewById<EditText>(R.id.editTexApptName)
        var passwordEditText=findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton=findViewById<Button>(R.id.buttonBookApp)
        var registerTextView= findViewById<TextView>(R.id.textView5)

        loginButton.setOnClickListener {
           /* val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/

           val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Handle login logic here
                if (db.login(username,password)==1) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                    val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }


        registerTextView.setOnClickListener {

            val intent = Intent(this, registerActivity2::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}