package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderFood {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("quantity")
    @Expose
    var name: String? = null
}