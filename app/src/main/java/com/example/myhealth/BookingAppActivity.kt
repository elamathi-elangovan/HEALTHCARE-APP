package com.example.myhealth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingAppActivity : AppCompatActivity() {

    private lateinit var bookedAppointments: Array<Array<String>>
    private lateinit var btnBack: Button
    private lateinit var listView: ListView
    private lateinit var sa: SimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_app)

        btnBack = findViewById(R.id.buttonbookback)
        listView = findViewById(R.id.listViewBookList)

        btnBack.setOnClickListener {
            startActivity(Intent(this@BookingAppActivity, MainActivity::class.java))
        }

        val db = Database(applicationContext, "myhealth", null, 1)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username: String = sharedPreferences.getString("username", "").toString()

        val dbData = db.getBookedAppointments(username)
        bookedAppointments = Array(dbData.size) { Array(5) { "" } }

        for (i in bookedAppointments.indices) {
            val arrData = dbData[i].toString()
            val strData = arrData.split(java.util.regex.Pattern.quote("$"))

            bookedAppointments[i][0] = strData[0]
            bookedAppointments[i][1] = strData[1]
            bookedAppointments[i][2] = strData[2]
            bookedAppointments[i][3] = strData[3]
            bookedAppointments[i][4] = strData[4]
        }

        val list = ArrayList<HashMap<String, String>>()
        for (i in bookedAppointments.indices) {
            val item = HashMap<String, String>()
            item["line1"] = bookedAppointments[i][0]
            item["line2"] = bookedAppointments[i][1]
            item["line3"] = bookedAppointments[i][2]
            item["line4"] = bookedAppointments[i][3]
            item["line5"] = bookedAppointments[i][4]
            list.add(item)
        }

        sa = SimpleAdapter(
            this, list,
            R.layout.doctorslist,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee)
        )
        listView.adapter = sa

        // Handle item click on listView
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as HashMap<String, String>
            val titleAndFullname = selectedItem["line1"] ?: ""
            val address = selectedItem["line2"] ?: ""
            val contact = selectedItem["line3"] ?: ""
            val date = selectedItem["line4"] ?: ""
            val time = selectedItem["line5"] ?: ""

            // Create intent to navigate to BookingOrderDetActivity
            val intent = Intent(this@BookingAppActivity, AppointmentActivity::class.java).apply {
                putExtra("titleAndFullname", titleAndFullname)
                putExtra("address", address)
                putExtra("contact", contact)
                putExtra("date", date)
                putExtra("time", time)
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
