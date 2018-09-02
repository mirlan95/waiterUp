package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.data.network.OrderBackStatus
import com.example.mirlan.waiterup.data.network.OrderItem
import com.example.mirlan.waiterup.data.network.Test
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.ProgressDialog
import com.example.mirlan.waiterup.view.adapter.OrderAdapter
import kotlinx.android.synthetic.main.activity_order_last_control.*
import java.lang.Math.ceil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil

class OrderLastControlActivity : AppCompatActivity() {

    private var orderList:ArrayList<Food>? = null
    private var layoutManager: LinearLayoutManager? = null
    private var mOrderAdapter: OrderAdapter? = null
    private var date:String? = null
    private var tableId:Int = 0
    private var isChecked:Int = 0
  //  private lateinit var mTableEdit:EditText
    private var ord:Test?=null

    companion object {
        var isClock:Int = 1
        @SuppressLint("UseSparseArrays")
        var orderArrayList : ArrayList<Test>?= null

        @SuppressLint("SetTextI18n")
        fun initTotalText(orderLastControlActivity: OrderLastControlActivity){
            var sum:Float = 0F
            for(i in 0 until orderLastControlActivity.orderList!!.size)
                sum += orderLastControlActivity.orderList!![i].price * orderLastControlActivity.orderList!![i].quantity
            val k :Float
            k = sum + ceil((sum/10))
            ceil(k)
            orderLastControlActivity.txt_total.text = k.toString()+" сом"
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_last_control)

        val picker = findViewById<TimePicker>(R.id.timePicker)
        picker.setIs24HourView(true)
       // mTableEdit = findViewById(R.id.edit_table)
        orderList = intent.getParcelableArrayListExtra<Food>("list")
        isClock = intent.getIntExtra("CLOCK",1)
        isChecked = if (intent.getBooleanExtra("EXTERNAL",false)) 1 else 0
        if(isClock==0) picker.visibility = View.VISIBLE

        Companion.initTotalText(this)
        initRecylerView()

        picker.setOnTimeChangedListener { _: TimePicker, hour: Int, min: Int ->

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = sdf.format(Date())
            date = "$currentDate $hour:$min:00"
            //Toast.makeText(this,date,Toast.LENGTH_LONG).show()
        }
    }
    private fun initRecylerView() {

        initMap()
        order_recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        order_recyclerview.layoutManager = layoutManager
        mOrderAdapter = OrderAdapter(orderList?.filter { it -> it.quantity > 0 } as ArrayList<Food>)
        order_recyclerview.adapter = mOrderAdapter
        //dialog.dismiss()
    }

    fun sendOrder(v:View){
        alertDialogShow()
    }

    private fun sendOrderToServer(){

        tableId = Integer.parseInt(edit_table.text.toString().trim())
        Toast.makeText(this,ord?.quantity.toString() + date.toString(),Toast.LENGTH_LONG).show()

        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
        Network.request(Api.provideApi().sendOrder(SaveSharedPreference.getAccessToken(this),SaveSharedPreference.getUserId(this).toInt(),tableId,isChecked,date.toString(), orderArrayList!!),
                NetworkCallback<OrderBackStatus>().apply {
                    success = {
                        Log.e("Success",it.createDate)
                        dialog.dismiss()
                        txt_total.text = "hi"+ it.foods
                        //routingToOrderHistory()
                    }
                    error = {
                        Log.e("ERRORR",it.localizedMessage)
                        dialog.dismiss()
                    }
                }
        )
    }
    private fun routingToOrderHistory(){
        val intent = Intent(this,OrderHistoryActivity::class.java)
        startActivity(intent)
    }

    private fun alertDialogShow(){
        val alert =  AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.send_order_title))
                .setMessage(resources.getString(R.string.send_order))
                .setNegativeButton(resources.getString(R.string.no), null)
                .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                    sendOrderToServer()
                }
        alert.show()
    }
    private fun initMap(){

        for(i in 0 until orderList!!.size) {
            if(orderList!![i].quantity>0){
            ord = Test(orderList!![i].id,orderList!![i].quantity)
            orderArrayList?.add(ord!!)
            }
        }
        //map[orderList!![i].id.toString()] = orderList!![i].quantity
    }
    fun returnHome(view: View) {
        super.onBackPressed()
    }
}
