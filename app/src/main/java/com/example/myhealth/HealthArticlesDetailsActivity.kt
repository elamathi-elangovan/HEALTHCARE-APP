package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HealthArticlesDetailsActivity : AppCompatActivity() {
    private  lateinit var tv1: TextView
    private lateinit var img: ImageView
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_articles_details)

        btnBack = findViewById(R.id.buttonbackart)
        tv1 = findViewById(R.id.cardHealthArt)
        img = findViewById(R.id.imageview)

       /* val intent = intent
        tv1.text = intent.getStringExtra("text1")

        val bundle = intent.extras
        if (bundle != null) {
            val resId = bundle.getInt("text2")?:0
            img.setImageResource(resId)
        }
        btnBack.setOnClickListener {
            startActivity(Intent(this@HealthArticlesDetailsActivity, HealthArticleActivity::class.java))
        }*/

        img.setImageResource(R.drawable.article1)
        val intent = intent
        tv1.text = intent.getStringExtra("text1")

        btnBack.setOnClickListener {
            startActivity(Intent(this@HealthArticlesDetailsActivity, HealthArticleActivity::class.java))
        }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}