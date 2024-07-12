package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HealthArticleActivity : AppCompatActivity() {
    private lateinit var healthDetails: Array<Array<String>>
    private lateinit var images: Array<Int>

    private lateinit var btnBack: Button
    private lateinit var listView: ListView
    private lateinit var sa: SimpleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_article)

        // Initialize healthDetails and images arrays
        healthDetails = arrayOf(
            arrayOf("Health Detail 1","","","", "click for more details.."),
            arrayOf("Health Detail 2","","","", "click for more details..") ,
            arrayOf("Health Detail 3","","","", "click for more details.."),
            arrayOf("Health Detail 4","","","", "click for more details.."),
            arrayOf("Health Detail 5","","","", "click for more details..")

        )

        images = arrayOf(
            R.drawable.article1,
            R.drawable.article2,
            R.drawable.article3,
            R.drawable.article4,
            R.drawable.article5

        )
        listView = findViewById(R.id.listViewHealthArt)
        btnBack = findViewById(R.id.buttonHeaArt)

        btnBack.setOnClickListener {
            startActivity(Intent(this@HealthArticleActivity, MainActivity::class.java))
        }

        val list = ArrayList<HashMap<String, String>>()
        for (i in healthDetails.indices) {
            val item = HashMap<String, String>()
            item["line1"] = healthDetails[i][0]
            item["line2"] = healthDetails[i][1]
            list.add(item)
        }

        sa = SimpleAdapter(
            this,
            list,
            R.layout.doctorslist,
            arrayOf("line1", "line2","line3","line4","line5"),
            intArrayOf(R.id.linea, R.id.lineb,R.id.linec,R.id.lined,R.id.linee)
        )
        listView.adapter = sa

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@HealthArticleActivity, HealthArticlesDetailsActivity::class.java)
            intent.putExtra("text1", healthDetails[position][0])
            intent.putExtra("image", images[position])
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}