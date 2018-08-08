package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.data.network.OrderItem
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.ProgressDialog
import com.example.mirlan.waiterup.view.adapter.OrderItemAdapter
import kotlinx.android.synthetic.main.activity_about_order.*
import android.app.Activity

class AboutOrderActivity : AppCompatActivity() {

    private var orderId: Int = 0
    private var mOrderItemList:ArrayList<OrderItem>? = null
    private var mOrderItemAdapter:OrderItemAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var moreOrderArrayList: ArrayList<Food>? = null
    private var orderItem: OrderItem?=null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        text_date.text = intent.getStringExtra("DATE")?.substring(11..15)
        text_table.text = "Стол: " + intent.getIntExtra("TABLE",0).toString()
        text_total_sum.text = intent.getIntExtra("SUM",0).toString() + " сом"
        orderId = intent.getIntExtra("ORDER_ID",0)

        getOrderItems()

    }
    fun addOrderBtn(v:View){

        val intent = Intent(this,NewOrderActivity::class.java)
        intent.putExtra("MAIN",2)
        startActivityForResult(intent,2)

    }
    fun printCheckBtn(v:View){printCheck()}

    fun closeOrderBtn(v: View){ closeOrder() }

    private fun getOrderItems() {
        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
        Network.request(Api.provideApi().getOrderItems(orderId,SaveSharedPreference.getAccessToken(this),SaveSharedPreference.getUserId(this).toInt()),
                NetworkCallback<List<OrderItem>>().apply {
                    success = {
                        dialog.dismiss()
                        mOrderItemList = ArrayList(it)
                        initRecyclerview()

                    }
                    error = {
                        dialog.dismiss()
                        Log.e("ERROR",it.localizedMessage)
                        msgToUser(false)
                    }
                }
        )

    }

    private fun printCheck(){
        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
        Network.request(Api.provideApi().printCheck(orderId),
                NetworkCallback<Boolean>().apply {
                    success = {

                        dialog.dismiss()
                        msgToUser(true)

                    }
                    error = {
                        dialog.dismiss()
                        msgToUser(false)
                    }
                }
        )
    }

    private fun closeOrder(){
        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
        Network.request(Api.provideApi().closeOrder(orderId,SaveSharedPreference.getAccessToken(this)),
                NetworkCallback<Boolean>().apply {
                    success = {

                        dialog.dismiss()
                        msgToUser(true)

                    }
                    error = {
                        dialog.dismiss()
                        msgToUser(false)
                    }
                }
        )
    }
    private fun initRecyclerview(){

        recycler_about_order.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycler_about_order.layoutManager = layoutManager
        mOrderItemAdapter = OrderItemAdapter(this.mOrderItemList!!)
        recycler_about_order.adapter = mOrderItemAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == 2) {

            if (resultCode == Activity.RESULT_OK) {

                moreOrderArrayList = data.getParcelableArrayListExtra<Food>("list")
                addListToAdapter()

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // some stuff that will happen if there's no result
            }
        }
    }
    private fun addListToAdapter(){

        orderItem = OrderItem()
        for(i in 0 until moreOrderArrayList!!.size){
            if(moreOrderArrayList!![i].quantity > 0){
                orderItem?.foodName =  moreOrderArrayList!![i].name
                orderItem?.mQuantity = moreOrderArrayList!![i].quantity
                orderItem?.price = moreOrderArrayList!![i].price
                mOrderItemList?.add(orderItem!!)}
        }
        mOrderItemAdapter?.notifyDataSetChanged()
    }
    private fun msgToUser(b: Boolean) {
        if(b) {
            Toast.makeText(this,R.string.success,Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }else
            Toast.makeText(this,R.string.errorInt,Toast.LENGTH_LONG).show()

    }
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        android.R.id.home ->{ onBackPressed()
            true }
        else -> false
    }
}
