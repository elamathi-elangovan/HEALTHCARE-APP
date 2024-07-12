package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Doc_DetailsActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private lateinit var btn: Button
    private lateinit var sa: SimpleAdapter
    private val list = ArrayList<HashMap<String, String>>()
    private lateinit var doctorDetails: Array<Array<String>>

    private val doctorDetails1 = arrayOf(
        arrayOf("Doctor Name: T.Arjun", "Hospital Address: Anna Nagar ", "Exp: 5yrs", "Mobile No: 98765 43210", "600"),
        arrayOf("Doctor Name: P.Rohit", "Hospital Address: Egmore ", "Exp: 15yrs", "Mobile No: 91234 56789", "900"),
        arrayOf("Doctor Name: S.Ishita", "Hospital Address: Adyar", "Exp: 8yrs", "Mobile No: 87654 32109", "300"),
        arrayOf("Doctor Name: D.Swapna", "Hospital Address: Ashok Nagar", "Exp: 6yrs", "Mobile No: 98980 06700", "500"),
        arrayOf("Doctor Name: A.Rajat", "Hospital Address: Kelambakkam", "Exp: 7yrs", "Mobile No: 76543 21098", "800")
    )

    private val doctorDetails2 = arrayOf(
        arrayOf("Doctor Name: N.Vaishanvi", "Hospital Address:Anna Nagar ", "Exp: 5yrs", "Mobile No:85432 10987", "600"),
        arrayOf("Doctor Name: S.Yash", "Hospital Address: Egmore", "Exp: 15yrs", "Mobile No: 74321 09876 ", "900"),
        arrayOf("Doctor Name: E.Pranav", "Hospital Address: Adyar", "Exp: 8yrs", "Mobile No: 93210 98765", "300"),
        arrayOf("Doctor Name: M.Aryan", "Hospital Address: Ashok Nagar", "Exp: 6yrs", "Mobile No: 92109 87654", "500"),
        arrayOf("Doctor Name: G.Minakshi", "Hospital Address: Kelambakkam", "Exp: 7yrs", "Mobile No: 71098 76543", "800")
    )

    private val doctorDetails3 = arrayOf(
        arrayOf("Doctor Name: S.Raja", "Hospital Address: Anna Nagar", "Exp: 4yrs", "Mobile No: 91098 76543", "200"),
        arrayOf("Doctor Name: P.Karthik", "Hospital Address: Egmore", "Exp: 5yrs", "Mobile No: 98712 34567", "300"),
        arrayOf("Doctor Name: M.Kavya", "Hospital Address: Adyar", "Exp: 7yrs", "Mobile No: 87651 23459", "300"),
        arrayOf("Doctor Name: V.Anika", "Hospital Address: Ashok Nagar", "Exp: 6yrs", "Mobile No: 76512 34980", "500"),
        arrayOf("Doctor Name: S.Saivishwa", "Hospital Address:Kelambakkam", "Exp: 7yrs", "Mobile No: 75423 19804", "600")
    )

    private val doctorDetails4 = arrayOf(
        arrayOf("Doctor Name: A.Riya", "Hospital Address: Anna Nagar", "Exp: 5yrs", "Mobile No: 94321 90872", "600"),
        arrayOf("Doctor Name: P.Aniket", "Hospital Address: Egmore", "Exp: 15yrs", "Mobile No: 73210 98765", "900"),
        arrayOf("Doctor Name: K.Nilesh", "Hospital Address: Adyar", "Exp: 8yrs", "Mobile No: 82109 87654", "300"),
        arrayOf("Doctor Name: D.Sona", "Hospital Address: Ashok Nagar", "Exp: 6yrs", "Mobile No: 921098 7654", "500"),
        arrayOf("Doctor Name: V.Ashok ", "Hospital Address: Kelambakkam", "Exp: 7yrs", "Mobile No: 70987 65432", "800")
    )

    private val doctorDetails5 = arrayOf(
        arrayOf("Doctor Name: N.Siddharth", "Hospital Address: Anna Nagar", "Exp: 5yrs", "Mobile No: 98765 43210", "1600"),
        arrayOf("Doctor Name: K.Priya", "Hospital Address: Egmore", "Exp: 15yrs", "Mobile No: 87654 32109", "1900"),
        arrayOf("Doctor Name: S.Shree", "Hospital Address: Adyar", "Exp: 8yrs", "Mobile No: 76543 21098", "1300"),
        arrayOf("Doctor Name: D.Akash", "Hospital Address: Ashok Nagar", "Exp: 6yrs", "Mobile No: 95432 10987", "1500"),
        arrayOf("Doctor Name: V.Ruturaj", "Hospital Address: Kelambakkam", "Exp: 7yrs", "Mobile No: 754321 0987", "1800")
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doc_details)

        tv = findViewById(R.id.textviewOrderdetail)
        btn = findViewById(R.id.buttonOrderback)

        val it = intent
        val title = it.getStringExtra("title")
        tv.text = title


        doctorDetails = when (title) {
            "Family Physicians" -> doctorDetails1
            "Dietician" -> doctorDetails2
            "Dentist" -> doctorDetails3
            "Surgeon" -> doctorDetails4
            else -> doctorDetails5
        }





        btn.setOnClickListener {
            startActivity(Intent(this@Doc_DetailsActivity, FindDocActivity::class.java))
        }

        for (detail in doctorDetails) {
            val item = HashMap<String, String>()
            item["line1"] = detail[0]
            item["line2"] = detail[1]
            item["line3"] = detail[2]
            item["line4"] = detail[3]
            item["line5"] = "Cons Fees: ${detail[4]}/-"
            list.add(item)
        }

        sa = SimpleAdapter(
            this,
            list,
            R.layout.doctorslist,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee)
        )

        val lst = findViewById<ListView>(R.id.listVieworderList)
        lst.adapter = sa

        lst.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val intent = Intent(this@Doc_DetailsActivity, AppointmentActivity::class.java)
            intent.putExtra("text1", title)
            intent.putExtra("text2", doctorDetails[i][0])
            intent.putExtra("text3", doctorDetails[i][1])
            intent.putExtra("text4", doctorDetails[i][3])
            intent.putExtra("text5", doctorDetails[i][4])
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}