package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food

class OrderAdapter(private var mOrderList: ArrayList<Food>): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var context: Context? = null
    var map = HashMap<String, String>()

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mQuantity.visibility = View.VISIBLE
        holder.mFoodName.setTextColor(R.color.red)
        holder.mFoodName.text = mOrderList[position].name
        holder.mQuantity.text = mOrderList[position].quantity.toString()

        holder.linear.setOnClickListener {

            val numberArray = Array(50) { i -> (i).toString() }

            val builder = AlertDialog.Builder(this.context!!)

            builder.setTitle(mOrderList[position].name)
            builder.setItems(numberArray) { dialog, which ->

                if (numberArray[which].toInt() > 0) {

                    //summ(mFoodList[position].price * numberArray[which].toInt())

                    holder.mQuantity.text = numberArray[which]
                } else {
                   // minn(mFoodList[position].price * numberArray[which].toInt())

                }
                mOrderList[position].quantity = numberArray[which].toInt()
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