package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.data.network.Test
import com.example.mirlan.waiterup.view.OrderLastControlActivity
import com.example.mirlan.waiterup.view.OrderLastControlActivity.Companion.initTotalText
import com.example.mirlan.waiterup.view.OrderLastControlActivity.Companion.orderArrayList

class OrderAdapter(private var mOrderList: ArrayList<Food>): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var context: Context? = null

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mFoodName.text = mOrderList[position].name

        if(mOrderList[position].quantity > 0){
            holder.mQuantity.text = mOrderList[position].quantity.toString()
            holder.mQuantity.visibility = View.VISIBLE
            holder.mFoodName.setTextColor(Color.parseColor("#ff0000"))
        }else {
            holder.mQuantity.visibility = View.INVISIBLE
            holder.mFoodName.setTextColor(Color.parseColor("#000000"))
        }

        holder.linear.setOnClickListener {

            val numberArray = Array(50) { i -> (i).toString() }

            val builder = AlertDialog.Builder(this.context!!)

            builder.setTitle(mOrderList[position].name)
            builder.setItems(numberArray) { dialog, which ->


                mOrderList[position].quantity = numberArray[which].toInt()
                initTotalText(context as OrderLastControlActivity)

                if (mOrderList[position].quantity > 0) {
                    val k = Test(mOrderList[position].id,mOrderList[position].quantity)
                    orderArrayList?.add(k)

                    holder.mQuantity.visibility = View.VISIBLE
                    holder.mFoodName.setTextColor(Color.parseColor("#ff0000"))
                    holder.mQuantity.text = mOrderList[position].quantity.toString()

                } else {

                    //orderArrayList?.get(orderArrayList?.find { it -> it.id == mOrderList[position].id })//removeAt(0)//removeIf { it -> it.id == mOrderList[position].id }
                    holder.mFoodName.setTextColor(Color.parseColor("#000000"))
                    holder.mQuantity.visibility = View.GONE
                    notifyDataSetChanged()
                }
                dialog.dismiss()
            }
            builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }

            val alert = builder.create()
            alert.show()

        }

    }
    fun summ(tot:Int) {
        //total += tot
    }
    fun minn(tot:Int){
       // total -= tot
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.menu_raw, parent, false)
        this.context = parent.context
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = mOrderList.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mFoodName = itemView.findViewById<TextView>(R.id.food_name)!!
        val mQuantity = itemView.findViewById<TextView>(R.id.food_quantity)!!
        val isHave = itemView.findViewById<TextView>(R.id.sum_food)!!
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)!!
        val categoryId:Int = 0

    }



}