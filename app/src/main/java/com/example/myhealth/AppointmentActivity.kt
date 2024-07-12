package com.example.myhealth

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AppointmentActivity : AppCompatActivity() {

    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var ed3: EditText
    private lateinit var ed4: EditText
    private lateinit var tv: TextView
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var bookButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointment)

        tv = findViewById(R.id.textView4)
        ed1 = findViewById(R.id.editTexApptName)
        ed2 = findViewById(R.id.editTextAppAddress)
        ed3 = findViewById(R.id.editTextAppcontact)
        ed4 = findViewById(R.id.editTextAppFees)
        dateButton = findViewById(R.id.buttonAppDAte)
        timeButton = findViewById(R.id.buttonAppTime)
        bookButton = findViewById(R.id.buttonBookApp)
        backButton = findViewById(R.id.buttonBookAppBack)

        ed1.keyListener = null
        ed2.keyListener = null
        ed3.keyListener = null
        ed4.keyListener = null

        val it = intent
        val title = it.getStringExtra("text1") ?: ""
        val fullname1 = it.getStringExtra("text2") ?: ""
        val address = it.getStringExtra("text3") ?: ""
        val contact = it.getStringExtra("text4") ?: ""
        val fees = it.getStringExtra("text5") ?: ""

        tv.text = title
        ed1.setText(fullname1)
        ed2.setText(address)
        ed3.setText(contact)
        ed4.setText("Cons Fees: $fees/-")

        initDatePicker()
        dateButton.setOnClickListener {
            datePickerDialog.show()
        }

        initTimePicker()
        timeButton.setOnClickListener {
            timePickerDialog.show()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this@AppointmentActivity, FindDocActivity::class.java))
        }

        bookButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("username", "") ?: ""

            val date = dateButton.text.toString()
            val time = timeButton.text.toString()
            val pincode = 12345 // Replace with actual pincode value if available
            val amount = fees.toFloatOrNull() ?: 0.0f
            val otype = "type" // Replace with actual type value

            if (username.isEmpty()) {
                Toast.makeText(applicationContext, "No user found", Toast.LENGTH_LONG).show()
            } else {
                val db = Database(applicationContext, "myhealth", null, 1)
                if (db.checkAppointmentExists(username, "$title=>$fullname1", address, contact, date, time)) {
                    Toast.makeText(applicationContext, "Appointment already booked", Toast.LENGTH_LONG).show()
                } else {
                    db.addOrder(username, "$title=>$fullname1", address, contact, pincode, date, time, amount, otype)
                    Toast.makeText(applicationContext, "Your appointment is booked successfully", Toast.LENGTH_LONG).show()
                    navigateToBookingOrderDetActivity()
                }
            }
        }
    }

    private fun initDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val monthAdjusted = month + 1
            val date = "$dayOfMonth/$monthAdjusted/$year"
            dateButton.text = date
        }

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val style = AlertDialog.THEME_HOLO_DARK

        datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
        datePickerDialog.datePicker.minDate = cal.timeInMillis + 86400000
    }

    private fun initTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val time = "$hourOfDay:$minute"
            timeButton.text = time
        }

        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)
        val style = AlertDialog.THEME_HOLO_DARK
        val is24HourFormat = true

        timePickerDialog = TimePickerDialog(this, style, timeSetListener, hour, minute, true)
    }

    private fun navigateToBookingOrderDetActivity() {
        val intent = Intent(this@AppointmentActivity, BookingAppActivity::class.java)
        startActivity(intent)
        finish()  // Optional: Close AppointmentActivity after navigating to BookingAppActivity
    }
}
