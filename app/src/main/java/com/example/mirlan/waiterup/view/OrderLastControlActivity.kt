package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.view.adapter.OrderAdapter
import kotlinx.android.synthetic.main.activity_new_order.*
import kotlinx.android.synthetic.main.activity_order_last_control.*

class OrderLastControlActivity : AppCompatActivity() {

    private var mFloorEdit:EditText?=null
    private var mTableEdit:EditText?=null
    private var orderList:ArrayList<Food>? = null
    private var layoutManager: LinearLayoutManager? = null
    private var mOrderAdapter: OrderAdapter? = null
    private var minute:Int = 0
    private var hour:Int = 0

    companion object {
        var isClock:Int = 1
    }
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_last_control)

        val picker = findViewById<TimePicker>(R.id.timePicker)
        picker.setIs24HourView(true)

        orderList = intent.getParcelableArrayListExtra<Food>("list")
        isClock = intent.getIntExtra("CLOCK",1)
        if(isClock==0) picker.visibility = View.VISIBLE
        txt_total.text =  " сом"
        initRecylerView()

        picker.setOnTimeChangedListener { _: TimePicker, hourr: Int, min: Int ->
            minute = min
            hour = hourr
        }
    }
    private fun initRecylerView() {

        order_recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        order_recyclerview.layoutManager = layoutManager
        mOrderAdapter = OrderAdapter(orderList!!)
        order_recyclerview.adapter = mOrderAdapter
        //dialog.dismiss()
    }

    private fun sendOrder(v:View){

    }

    fun returnHome(view: View) {
        super.onBackPressed()
    }
}
