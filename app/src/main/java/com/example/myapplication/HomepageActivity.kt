package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HomepageActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_layout)
        val extraData = intent.getStringExtra("extra_data")
        val log:TextView = findViewById(R.id.textViewlog)
        val password:TextView = findViewById(R.id.textViewpassword)
//        显示用户信息
        if (extraData!=" "){
            log.setText("我是"+extraData.toString().split(" ")[0])
            password.setText("我的密码是"+extraData.toString().split(" ")[1])
        }
        else{
            log.setText("我是XXXXX")
            password.setText("我的密码是XXXXX")
        }

        val buttonchange: Button = findViewById(R.id.buttonchange)
        buttonchange.setOnClickListener {
            finish()
        }

        val buttonout2: Button = findViewById(R.id.buttonout2)
        buttonout2.setOnClickListener {
            ActivityCollector.finishAll()
        }

//        进入录入运单界面
        val buttonaddWaybill:Button = findViewById(R.id.buttonaddWaybill)
        buttonaddWaybill.setOnClickListener {
            val intent = Intent(this,AddwaybillActivity::class.java)
            startActivity(intent)
        }

//        进入查询本地运单界面
        val buttonquerylocal:Button = findViewById(R.id.buttonquerylocal)
        buttonquerylocal.setOnClickListener {
            val intent = Intent(this,QuerylocalActivity::class.java)
            startActivity(intent)
        }

//        进入查询公司运单XML界面
        val buttonqueryxml:Button = findViewById(R.id.buttonqueryxml)
        buttonqueryxml.setOnClickListener {
            val intent = Intent(this,QueryxmlActivity::class.java)
            startActivity(intent)
        }

//        进入查询公司运单JSon界面
        val buttonqueryjson:Button = findViewById(R.id.buttonqueryjson)
        buttonqueryjson.setOnClickListener {
            val intent = Intent(this,QueryjsonActivity::class.java)
            startActivity(intent)
        }
    }
}