package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User{
     @SerializedName("id")
     @Expose
     var userId: Int = 0

     @SerializedName("name")
     @Expose
     var name: String? = null

     override fun toString(): String {
         // return super.toString()
          return "$name"
     }
}