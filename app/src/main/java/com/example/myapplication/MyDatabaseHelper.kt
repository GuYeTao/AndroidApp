package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

public class MyDatabaseHelper(val context:Context,name:String,version:Int):
        SQLiteOpenHelper(context,name,null,version){

    private val createLogin = "create table Login ("+
        " id integer primary key autoincrement,"+
        "password text)"
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(createLogin)
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
