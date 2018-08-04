package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.OrderHistory
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.ProgressDialog
import com.example.mirlan.waiterup.view.adapter.OrderHistoryAdapter
import kotlinx.android.synthetic.main.activity_order_history.*

class OrderHistoryActivity : AppCompatActivity() {

    private var mOrderHistoryList: ArrayList<OrderHistory> ? = null
    private var layoutManager: LinearLayoutManager? = null
    private var mOrderHistoryAdapter:OrderHistoryAdapter? = null
    private var orderTotal: Int = 0

    companion object {
        var isOpen:Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isOpen = intent.getIntExtra("HISTORY",0)
        if(isOpen==0)
            supportActionBar?.title = getString(R.string.closedOrder)
        else
            supportActionBar?.title = getString(R.string.openOrder)

        getOrder()

    }
    fun updateBtn(v: View){
        getOrder()
    }

    private fun getOrder() {

        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
        Network.request(Api.provideApi().getOrderHistory(SaveSharedPreference.getUserId(this).toInt(),SaveSharedPreference.getAccessToken(this),isOpen),
                NetworkCallback<List<OrderHistory>>().apply {
                    success = {
                        mOrderHistoryList = ArrayList(it)
                        initRecyclerview()
                        dialog.dismiss()
                    }
                    error = {
                        dialog.dismiss()
                    }
                }
        )

    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerview() {
        orderTotal = 0
        for(i in 0 until mOrderHistoryList!!.size)
            orderTotal += mOrderHistoryList!![i].orderTotal

        txt_order_total.text = orderTotal.toString() + " сом"

        order_recycler.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        order_recycler.layoutManager = layoutManager
        mOrderHistoryAdapter = OrderHistoryAdapter(this.mOrderHistoryList!!)
        order_recycler.adapter = mOrderHistoryAdapter

    }
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home ->{
            onBackPressed()
            true
        }
        else -> false

    }

}
