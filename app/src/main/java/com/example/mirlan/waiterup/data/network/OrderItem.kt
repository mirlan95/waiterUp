package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderItem {


    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("food_id")
    @Expose
    var foodId: Int = 0

    @SerializedName("order_id")
    @Expose
    var orderId: Int = 0

    @SerializedName("quantity")
    @Expose
    var mQuantity: Int = 0

    @SerializedName("total")
    @Expose
    var mTotal: Int = 0

    @SerializedName("created_at")
    @Expose
    var createDate: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedDate: String? = null

    @SerializedName("name")
    @Expose
    var foodName: String? = null
    @SerializedName("price")
    @Expose
    var price: Int = 0
}