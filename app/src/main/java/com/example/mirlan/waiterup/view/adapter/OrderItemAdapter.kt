package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.data.network.OrderItem
import com.example.mirlan.waiterup.view.AboutOrderActivity
import com.example.mirlan.waiterup.view.OrderHistoryActivity

class OrderItemAdapter(private var mOrderItemList: ArrayList<OrderItem>): RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    private var context: Context? = null


    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mItemName.text = mOrderItemList[position].foodName
        holder.mPriceQuantity.visibility = View.VISIBLE
        holder.mItemQuantity.visibility = View.VISIBLE
        holder.mItemQuantity.text = mOrderItemList[position].mQuantity.toString()
        holder.mPriceQuantity.text = "x " + mOrderItemList[position].price.toString() +  " = " + (mOrderItemList[position].price * mOrderItemList[position].mQuantity).toString()
        holder.linear.setOnClickListener {

            val numberArray = Array(50) { i -> (i).toString() }

            val builder = AlertDialog.Builder(this.context!!)

            builder.setTitle(mOrderItemList[position].foodName)
            builder.setItems(numberArray) { dialog, which ->

                mOrderItemList[position].mQuantity = numberArray[which].toInt()

                if (numberArray[which].toInt() > 0) {

                    holder.mItemQuantity.text = mOrderItemList[position].mQuantity.toString()
                   // holder.mPriceQuantity.text = "x " + mOrderItemList[position].price.toString() +  " = " + (mOrderItemList[position].price * mOrderItemList[position].mQuantity).toString()

                } else {

                }
                //mOrderItemList[position].quantity = numberArray[which].toInt()
                notifyDataSetChanged()
                dialog.dismiss()
            }
            builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }

            val alert = builder.create()
            alert.show()

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.menu_raw, parent, false)
        this.context = parent.context
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = mOrderItemList.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mItemName = itemView.findViewById<TextView>(R.id.food_name)!!
        val mItemQuantity = itemView.findViewById<TextView>(R.id.food_quantity)!!
        val mPriceQuantity = itemView.findViewById<TextView>(R.id.sum_food)!!
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)!!

    }
}