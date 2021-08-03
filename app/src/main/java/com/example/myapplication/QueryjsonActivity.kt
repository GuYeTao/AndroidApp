package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class QueryjsonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queryjson)
        val buttonout6: Button = findViewById(R.id.buttonout6)
        buttonout6.setOnClickListener {
            finish()
        }
        sendRequestWithHttpURLConnection()
    }
    private fun sendRequestWithHttpURLConnection(){
        thread {
            var connection: HttpURLConnection?=null
            try {
                val response = StringBuilder()
                val url = URL("http://60.12.122.142:6080/simulated-Waybills-db.json")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                if (response!=null){
                parseJSONWithJSONObject(response.toString())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }
    private fun showResponse(response:String){
        runOnUiThread {
            val responsetext: TextView = findViewById(R.id.textviewjson)
            responsetext.text = response
        }
    }
    private fun parseJSONWithJSONObject(jsonData:String){
        try {
            val jsonData1 = jsonData.substring(21,jsonData.length-1)
            val jsonArray = JSONArray(jsonData1)
            var datas = "_____________________________________________\n"
            val data = arrayListOf("")
            for(i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val waybillNo = jsonObject.getString("waybillNo")
                val consignor = jsonObject.getString("consignor")
                val consignorPhoneNumber = jsonObject.getString("consignorPhoneNumber")
                val consignee = jsonObject.getString("consignee")
                val consigneePhoneNumber = jsonObject.getString("consigneePhoneNumber")
                val transportationDepartureStation = jsonObject.getString("transportationDepartureStation")
                val transportationArrivalStation = jsonObject.getString("transportationArrivalStation")
                val goodsDistributionAddress = jsonObject.getString("goodsDistributionAddress")
                val goodsName = jsonObject.getString("goodsName")
                val numberOfPackages = jsonObject.getString("numberOfPackages")
                val freightPaidByTheReceivingParty = jsonObject.getString("freightPaidByTheReceivingParty")
                val freightPaidByConsignor = jsonObject.getString("freightPaidByConsignor")
//                datas = (datas+waybillNo+consignor+consignorPhoneNumber+consignee+consigneePhoneNumber+transportationDepartureStation
//                        +transportationArrivalStation+goodsDistributionAddress+goodsName+numberOfPackages+freightPaidByTheReceivingParty
//                        + freightPaidByConsignor+"\n")
                datas = (datas+transportationArrivalStation+"-"+transportationDepartureStation+" "+goodsName+" "+numberOfPackages+"件   No："+waybillNo+"\n收货人："+
                        consignee+"("+consigneePhoneNumber+")  到付"+freightPaidByTheReceivingParty+"元\n"+"_____________________________________________\n")
//                data.add(datas)
            }
            showResponse(datas)
//            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
//            val listviewjson: ListView = findViewById(R.id.listviewjson)
//            listviewjson.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}