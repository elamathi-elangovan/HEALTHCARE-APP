package com.example.myhealth

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        val qry1 = "create table users(username text,email text,password text)"
        db.execSQL(qry1)
        val qry2 = "create table cart(username text, titleandfullname text, address text,contactno text,data text,time text,amount real,otype text)"
        db.execSQL(qry2)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun register(username: String?, email: String?, Password: String?) {
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("email", email)
        cv.put("password", Password)
        val db = writableDatabase
        db.insert("users", null, cv)
        db.close()
    }

    fun login(username: String?, Password: String?): Int {
        var result = 0
        val str = arrayOfNulls<String>(2)
        str[0] = username
        str[1] = Password
        val db = readableDatabase
        val c = db.rawQuery("select * from users where username=? and password =?", str)
        if (c.moveToFirst()) {
            result = 1
        }

        return result
    }

    fun getOrderData(username: String): ArrayList<String> {
        val arr = ArrayList<String>()
        val db = readableDatabase
        val selectionArgs = arrayOf(username)
        val query = "SELECT * FROM orderplace WHERE username = ?"
        val c = db.rawQuery(query, selectionArgs)

        if (c.moveToFirst()) {
            do {
                arr.add("${c.getString(1)}$${c.getString(2)}$${c.getString(3)}$${c.getString(4)}$${c.getString(5)}$${c.getString(6)}$${c.getString(7)}$${c.getFloat(8)}$${c.getString(9)}")
            } while (c.moveToNext())
        }

        c.close()
        db.close()

        return arr
    }





    fun addOrder(
        username: String, titleAndFullname: String, address: String, contact: String, pincode: Int, date: String, time: String, amount: Float, otype: String
    ) {
        val cv = ContentValues().apply {
            put("username", username)
            put("fullname", titleAndFullname)
            put("address", address)
            put("contactno", contact)
            put("pincode", pincode)
            put("date", date)
            put("time", time)
            put("amount", amount)
            put("otype", otype)
        }
        val db = writableDatabase
        db.insert("orderplace", null, cv)
        db.close()
    }


    fun checkAppointmentExists(username: String, titleAndFullname: String, address: String, contact: String, date: String, time: String): Boolean{
        var result = false
        val str = arrayOf(username, titleAndFullname, address, contact, date, time)
        val db = readableDatabase
        val query = "SELECT * FROM orderplace WHERE username = ? AND fullname = ? AND address = ? AND contactno = ? AND date = ? AND time = ?"
        val c = db.rawQuery(query, str)

        if (c.moveToFirst()) {
            result = true
        }

        c.close()
        db.close()

        return result
    }

    fun getBookedAppointments(username: String): List<String> {
        val db = this.readableDatabase
        val query = "SELECT * FROM appointments WHERE username=?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val appointments = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val titleAndFullname = cursor.getString(cursor.getColumnIndexOrThrow("title_and_fullname"))
                val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val time = cursor.getString(cursor.getColumnIndexOrThrow("time"))

                appointments.add("$titleAndFullname$$address$$date$$time")
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return appointments
    }


}

