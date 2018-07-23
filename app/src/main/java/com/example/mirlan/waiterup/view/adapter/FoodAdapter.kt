package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import kotlinx.android.synthetic.main.activity_new_order.*

class FoodAdapter(private val mFoodList: ArrayList<Food>,private val listener: Listener):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    interface Listener{
        fun onItemClick(mFood: Food)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mFoodName.text = mFoodList[position].name


     //   if(mFoodList[position].isHave == 0){
            holder.bind(mFoodList[position], listener)
            //holder.isHave.visibility = View.GONE
        holder.isHave.typeface = Typeface.DEFAULT_BOLD
        holder.linear.setBackgroundColor(R.color.hint)

        holder.linear.setOnClickListener {

            holder.mFoodName.setTextColor(R.color.red)
            

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.menu_raw, parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = mFoodList.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mFoodName = itemView.findViewById<TextView>(R.id.food_name)
        val mQuantity = itemView.findViewById<TextView>(R.id.food_quatity)
        val isHave = itemView.findViewById<TextView>(R.id.sum_food)
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)


        fun bind(mFood :Food, listener: Listener){

            itemView.setOnClickListener{listener.onItemClick(mFood)}

        }
    }



}