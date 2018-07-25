package com.example.mirlan.waiterup.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.network.Food
import android.support.v7.app.AlertDialog
import android.widget.*


class FoodAdapter(private var mFoodList: ArrayList<Food>, ctx:Context):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private var context: Context? = null
    private var mFoodSortList: ArrayList<Food>? = null
    private var catId:Int = 0


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mFoodName.text = mFoodList[position].name
        holder.isHave.typeface = Typeface.DEFAULT_BOLD

        if(mFoodList[position].isHave == 0) {

            holder.isHave.visibility = View.VISIBLE

        }
        else if(mFoodList[position].isHave == 1){

            holder.linear.setOnClickListener {

                val numberArray = Array(50) { i -> (i).toString() }

                val builder = AlertDialog.Builder(this.context!!)

                    builder.setItems(numberArray) { dialog, which ->
                        if (numberArray[which].toInt() > 0) {
                            holder.mQuantity.visibility = View.VISIBLE
                            holder.mQuantity.text = numberArray[which]
                            //holder.mFoodName.setTextColor(R.color.black)
                        }else {
                                holder.mQuantity.visibility = View.INVISIBLE
                                //holder.mFoodName.setTextColor(R.color.black)
                        }
                        dialog.dismiss()
                    }
                    builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }

                val alert = builder.create()
                    alert.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.menu_raw, parent, false)
        this.context = parent.context
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = mFoodList.count()

    fun setItems(id: Int, list: ArrayList<Food>?){

        catId = id
       // Toast.makeText(context,catId,Toast.LENGTH_LONG).show()
        /*if(id==0)
            mFoodList = ArrayList(list)
        else
            mFoodList = ArrayList(list!!.filter { s -> s.catId == id })*/
        notifyItemRangeChanged(0,mFoodList.size - 1)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mFoodName = itemView.findViewById<TextView>(R.id.food_name)!!
        val mQuantity = itemView.findViewById<TextView>(R.id.food_quatity)!!
        val isHave = itemView.findViewById<TextView>(R.id.sum_food)!!
        val linear = itemView.findViewById<LinearLayout>(R.id.linear)!!
    }



}