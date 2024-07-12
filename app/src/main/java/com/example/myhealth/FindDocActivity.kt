package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindDocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_doc)

        val findDoctor = findViewById<CardView>(R.id.cardFindDocBack)
        findDoctor.setOnClickListener {
            startActivity(Intent(this@FindDocActivity, MainActivity::class.java))
            finish()  // Optional: Close FindDocActivity after navigating back
        }
        val familyphysician = findViewById<CardView>(R.id.cardFamDoc)
        familyphysician.setOnClickListener {
            val intent = Intent(this@FindDocActivity, Doc_DetailsActivity::class.java)
            intent.putExtra("title", "Family physicians")
            startActivity(intent)
        }

        val dietician = findViewById<CardView>(R.id.cardDietician)
        dietician.setOnClickListener {
            val intent = Intent(this@FindDocActivity, Doc_DetailsActivity::class.java)
            intent.putExtra("title", "Dietician")
            startActivity(intent)
        }

        val dentist = findViewById<CardView>(R.id.cardDentist)
        dentist.setOnClickListener {
            val intent = Intent(this@FindDocActivity, Doc_DetailsActivity::class.java)
            intent.putExtra("title", "Dentist")
            startActivity(intent)
        }

        val surgeon = findViewById<CardView>(R.id.cardSurgeon)
        surgeon.setOnClickListener {
            val intent = Intent(this@FindDocActivity, Doc_DetailsActivity::class.java)
            intent.putExtra("title", "Surgeon")
            startActivity(intent)
        }

        val cardiologist = findViewById<CardView>(R.id.cardCardio)
        cardiologist.setOnClickListener {
            val intent = Intent(this@FindDocActivity, Doc_DetailsActivity::class.java)
            intent.putExtra("title", "Cardiologist")
            startActivity(intent)
        }

    }
}