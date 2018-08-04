package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import android.support.v7.app.AlertDialog
import android.widget.*

class FoodAdapter(private var mFoodList: ArrayList<Food>):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private var context: Context? = null

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.mFoodName.text = mFoodList[position].name

        if(mFoodList[position].quantity > 0){
            holder.mQuantity.text = mFoodList[position].quantity.toString()
            holder.mQuantity.visibility = View.VISIBLE
            holder.mFoodName.setTextColor(Color.parseColor("#ff0000"))
        }else {
            holder.mQuantity.visibility = View.INVISIBLE
            holder.mFoodName.setTextColor(Color.parseColor("#000000"))
        }
            holder.linear.setOnClickListener {

                val numberArray = Array(50) { i -> (i).toString() }

                val builder = AlertDialog.Builder(this.context!!)

                builder.setTitle(mFoodList[position].name)
                builder.setItems(numberArray) { dialog, which ->

                    mFoodList[position].quantity = numberArray[which].toInt()

                    if (mFoodList[position].quantity > 0) {

                        holder.mQuantity.visibility = View.VISIBLE
                        holder.mFoodName.setTextColor(Color.parseColor("#ff0000"))
                        holder.mQuantity.text = mFoodList[position].quantity.toString()

                    } else {
                        holder.mFoodName.setTextColor(Color.parseColor("#000000"))
                        holder.mQuantity.visibility = View.GONE
                    }

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
    override fun getItemCount(): Int = mFoodList.count()

    fun update(filter: List<Food>?) {
     mFoodList = ArrayList(filter)
        // notifyDataSetChanged()
}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mFoodName = itemView.findViewById<TextView>(R.id.food_name)!!
        val mQuantity = itemView.findViewById<TextView>(R.id.food_quantity)!!
        val isHave = itemView.findViewById<TextView>(R.id.sum_food)!!
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)!!
        val categoryId:Int = 0

    }



}