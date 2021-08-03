package com.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        val buttonLogin:Button = findViewById(R.id.buttonlogin)
        val buttonout1:Button = findViewById(R.id.buttonout1)
        val buttoncreateDatabase:Button = findViewById(R.id.buttoncreateDatabase)
        val buttonaddData:Button = findViewById(R.id.buttonaddData)
//        建立用户数据库,查询数据库
        val dbHelper = MyDatabaseHelper(this,"Login.db",1)
        buttonLogin.setOnClickListener {
//            val intent = Intent("com.example.activitytest.ACTION_START")
            val log:EditText = findViewById(R.id.editTextlog)
            val password:EditText = findViewById(R.id.editTextpassword)
            val inputlog = log.text.toString()
            val inputpassword = password.text.toString()
//            登录逻辑判断
            val db = dbHelper.writableDatabase
            val cursor = db.query("Login",null,null,null,null,null,null)
            var flag = 0
            if (cursor.moveToFirst()){
                do{
                    val id = cursor.getString(cursor.getColumnIndex("id"))
                    val password = cursor.getString(cursor.getColumnIndex("password"))
                    if ((inputlog==id) and (inputpassword==password)){
                        val driveintent = Intent(this,HomepageActivity::class.java)
                        driveintent.putExtra("extra_data",inputlog+" "+inputpassword)
                        startActivity(driveintent)
                        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                        flag = 1
                        break
                    }
                }while (cursor.moveToNext())
            }
            cursor.close()
            if(flag==0){
                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
            }
//            Toast.makeText(this,inputlog,Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,inputpassword,Toast.LENGTH_SHORT).show()
        }
        buttonout1.setOnClickListener {
            finish()
        }
//        建立用户数据库使用
        buttoncreateDatabase.setOnClickListener{
            dbHelper.writableDatabase
        }
        buttonaddData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("id","20184408")
                put("password","123456")
            }
            db.insert("Login",null,values1)
            val values2 = ContentValues().apply {
                put("id","11111111")
                put("password","123456")
            }
            db.insert("Login",null,values2)
        }
    }
}