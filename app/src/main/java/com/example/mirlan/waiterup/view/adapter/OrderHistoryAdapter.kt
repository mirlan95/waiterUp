package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.OrderHistory
import com.example.mirlan.waiterup.view.AboutOrderActivity
import com.example.mirlan.waiterup.view.OrderHistoryActivity.Companion.isOpen


class OrderHistoryAdapter(private var mOrderHistoryList: ArrayList<OrderHistory>): RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    private var context: Context? = null


    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mDate.text = mOrderHistoryList[position].orderDate?.substring(11..15)
        holder.mTable.text = "Стол: " + (mOrderHistoryList[position].tableId.toString())
        holder.mTotal.text = mOrderHistoryList[position].orderTotal.toString() + " сом"


        if (isOpen == 1)
        holder.linear.setOnClickListener {

            val intent = Intent(context, AboutOrderActivity::class.java)
            intent.putExtra("DATE",mOrderHistoryList[position].orderDate)
            intent.putExtra("TABLE",mOrderHistoryList[position].tableId)
            intent.putExtra("SUM",mOrderHistoryList[position].orderTotal)
            intent.putExtra("ORDER_ID",mOrderHistoryList[position].id)
            context?.startActivity(intent)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_history_raw, parent, false)
        this.context = parent.context
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = mOrderHistoryList.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mDate = itemView.findViewById<TextView>(R.id.order_date)!!
        val mTable = itemView.findViewById<TextView>(R.id.order_table)!!
        val mTotal = itemView.findViewById<TextView>(R.id.order_total_sum)!!
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)!!

    }
}