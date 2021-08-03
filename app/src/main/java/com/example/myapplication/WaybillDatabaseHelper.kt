package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

public class WaybillDatabaseHelper(val context:Context,name:String,version:Int):
    SQLiteOpenHelper(context,name,null,version){

    private val createWaybill = "create table Waybill ("+
            " id integer primary key autoincrement,"+
            "destination text not null,"+
            "origin text,"+
            "producer text,"+
            "pphone text,"+
            "consumer text,"+
            "cphone text,"+
            "name text not null,"+
            "number integer not null,"+
            "paidprice real,"+
            "unpaidprice real)"
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(createWaybill)
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
