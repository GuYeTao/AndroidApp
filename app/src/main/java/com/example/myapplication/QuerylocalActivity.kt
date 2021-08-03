package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*

class QuerylocalActivity : AppCompatActivity() {
//    private val data = listOf("apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry","mango")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_querylocal)
        val buttonout4: Button = findViewById(R.id.buttonout4)
        buttonout4.setOnClickListener {
            finish()
        }
//        访问数据库
//        val data = arrayListOf("")
        var datas = "_____________________________________________\n"
        val dbHelper = MyDatabaseHelper(this,"Waybill.db",1)
        val db = dbHelper.writableDatabase
        val cursor = db.query("Waybill",null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do{
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val destination = cursor.getString(cursor.getColumnIndex("destination"))
                val origin = cursor.getString(cursor.getColumnIndex("origin"))
                val producer = cursor.getString(cursor.getColumnIndex("producer"))
                val pphone = cursor.getString(cursor.getColumnIndex("pphone"))
                val consumer = cursor.getString(cursor.getColumnIndex("consumer"))
                val cphone = cursor.getString(cursor.getColumnIndex("cphone"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val number = cursor.getString(cursor.getColumnIndex("number"))
                val paidprice = cursor.getString(cursor.getColumnIndex("paidprice"))
                val unpaidprice = cursor.getString(cursor.getColumnIndex("unpaidprice"))
                val value = destination+"-"+origin+" "+name+" "+number+"件   No："+id+"收货人："+consumer+"("+cphone+")  到付"+unpaidprice+"元"
//                data.add(value)
                datas = (datas+destination+"-"+origin+" "+name+" "+number+"件   No："+id+"\n收货人："+
                        consumer+"("+cphone+")  到付"+unpaidprice+"元\n"+"_____________________________________________\n")

            }while (cursor.moveToNext())
        }
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
//        val listviewlocal:ListView = findViewById(R.id.listviewlocal)
//        listviewlocal.adapter = adapter
    showResponse(datas)
    }
    private fun showResponse(response:String){
        runOnUiThread {
            val responsetext: TextView = findViewById(R.id.textviewlocal)
            responsetext.text = response
        }
    }
}