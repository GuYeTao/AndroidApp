package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception
import kotlin.concurrent.thread

class QueryxmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queryxml)
        val buttonout5: Button = findViewById(R.id.buttonout5)
        buttonout5.setOnClickListener {
            finish()
        }
//        val buttontry:Button = findViewById(R.id.justtry)
//        buttontry.setOnClickListener {
//            sendRequestWithOkHttp()
//        }
        sendRequestWithHttpURLConnection()
    }
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("http://60.12.122.142:6080/simulated-Waybills-db.xml")
                        .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData!=null){
                    Toast.makeText(this,"访问成功",Toast.LENGTH_SHORT).show()
//                    parseXMLWithPul(responseData)
//                    showResponse(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun sendRequestWithHttpURLConnection(){
        thread {
            var connection:HttpURLConnection?=null
            try {
                val response = StringBuilder()
                val url = URL("http://60.12.122.142:6080/simulated-Waybills-db.xml")
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
                    parseXMLWithPull(response.toString())
                }
//                showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }
    private fun showResponse(response:String){
        runOnUiThread {
            val responsetext:TextView = findViewById(R.id.textviewxml)
            responsetext.text = response
        }
    }
    private fun parseXMLWithPull(xmlData:String){
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var waybillNo = ""
            var consignor = ""
            var consignorPhoneNumber = ""
            var consignee = ""
            var consigneePhoneNumber = ""
            var transportationDepartureStation = ""
            var transportationArrivalStation = ""
            var goodsDistributionAddress = ""
            var goodsName = ""
            var numberOfPackages = ""
            var freightPaidByTheReceivingParty = ""
            var freightPaidByConsignor = ""
//            val data = arrayListOf("")
            var datas = "_____________________________________________\n"
            while (eventType!=XmlPullParser.END_DOCUMENT){
                val nodeName = xmlPullParser.name
                when(eventType){
                    XmlPullParser.START_TAG->{
                        when(nodeName){
                            "waybillNo" -> waybillNo = xmlPullParser.nextText()
                            "consignor" -> consignor = xmlPullParser.nextText()
                            "consignorPhoneNumber" -> consignorPhoneNumber = xmlPullParser.nextText()
                            "consignee" -> consignee = xmlPullParser.nextText()
                            "consigneePhoneNumber" -> consigneePhoneNumber = xmlPullParser.nextText()
                            "transportationDepartureStation" -> transportationDepartureStation = xmlPullParser.nextText()
                            "transportationArrivalStation" -> transportationArrivalStation = xmlPullParser.nextText()
                            "goodsDistributionAddress" -> goodsDistributionAddress = xmlPullParser.nextText()
                            "goodsName" -> goodsName = xmlPullParser.nextText()
                            "numberOfPackages" -> numberOfPackages = xmlPullParser.nextText()
                            "freightPaidByTheReceivingParty" -> freightPaidByTheReceivingParty = xmlPullParser.nextText()
                            "freightPaidByConsignor" -> freightPaidByConsignor = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG ->{
                        if ("waybillRecord"==nodeName){
//                            data.add(waybillNo+consignor+consignorPhoneNumber+consignee+consigneePhoneNumber+transportationDepartureStation
//                                    +transportationArrivalStation+goodsDistributionAddress+goodsName+numberOfPackages+freightPaidByTheReceivingParty
//                                    + freightPaidByConsignor)
//                            datas = (datas+waybillNo+consignor+consignorPhoneNumber+consignee+consigneePhoneNumber+transportationDepartureStation
//                                    +transportationArrivalStation+goodsDistributionAddress+goodsName+numberOfPackages+freightPaidByTheReceivingParty
//                                    +freightPaidByConsignor+"\n")
                            datas = (datas+transportationArrivalStation+"-"+transportationDepartureStation+" "+goodsName+" "+numberOfPackages+"件   No："+waybillNo+"\n收货人："+
                                    consignee+"("+consigneePhoneNumber+")  到付"+freightPaidByTheReceivingParty+"元\n"+"_____________________________________________\n")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
//            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
//            val listviewxml:ListView = findViewById(R.id.listviewxml)
//            listviewxml.adapter = adapter
            showResponse(datas)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}