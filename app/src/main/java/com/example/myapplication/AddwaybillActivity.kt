package com.example.myapplication

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddwaybillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwaybill)
        val buttonout3: Button = findViewById(R.id.buttonout3)
        buttonout3.setOnClickListener {
            finish()
        }
//        创建运单数据库
        val buttoncreateWaybillDatabase:Button = findViewById(R.id.buttoncreateWaybillDatabase)
        val dbHelper = WaybillDatabaseHelper(this,"Waybill.db",1)
        buttoncreateWaybillDatabase.setOnClickListener{
            dbHelper.writableDatabase
        }
//        保存运单
        val buttonWaybilladdDatabase:Button = findViewById(R.id.buttonWaybilladdDatabase)
        buttonWaybilladdDatabase.setOnClickListener {
            val destination: EditText = findViewById(R.id.inputdestination)
            val inputdestination = destination.text.toString()
            val producer: EditText = findViewById(R.id.inputproducer)
            val inputproducer = producer.text.toString()
            val pphone: EditText = findViewById(R.id.inputpphone)
            val inputpphone = pphone.text.toString()
            val consumer: EditText = findViewById(R.id.inputconsumer)
            val inputconsumer = consumer.text.toString()
            val cphone: EditText = findViewById(R.id.inputcphone)
            val inputcphone = cphone.text.toString()
            val name: EditText = findViewById(R.id.inputname)
            val inputname = name.text.toString()
            val number: EditText = findViewById(R.id.inputnumber)
            val inputnumber = number.text.toString()
            val paidprice: EditText = findViewById(R.id.inputpaidprice)
            val inputpaidprice = paidprice.text.toString()
            val unpaidprice: EditText = findViewById(R.id.inputunpaidprice)
            val inputunpaidprice = unpaidprice.text.toString()
            val db = dbHelper.writableDatabase
            if ((inputdestination!="") and (inputname!="") and (inputnumber!="")){
                val values1 = ContentValues().apply {
//                    put("id",100000)
                    put("destination",inputdestination)
                    put("origin","沈阳")
                    put("producer",inputproducer)
                    put("pphone",inputpphone)
                    put("consumer",inputconsumer)
                    put("cphone",inputcphone)
                    put("name",inputname)
                    put("number",inputnumber)
                    put("paidprice",inputpaidprice)
                    put("unpaidprice",inputunpaidprice)
                }
                db.insert("Waybill",null,values1)
//            提交后清空输入框
                destination.setText("")
                producer.setText("")
                pphone.setText("")
                consumer.setText("")
                cphone.setText("")
                name.setText("")
                number.setText("")
                paidprice.setText("")
                unpaidprice.setText("")
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"保存失败,红框项不能为空",Toast.LENGTH_SHORT).show()
            }
        }
    }
}