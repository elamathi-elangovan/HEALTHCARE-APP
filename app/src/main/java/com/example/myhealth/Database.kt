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
}
