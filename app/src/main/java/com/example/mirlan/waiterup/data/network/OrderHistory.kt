package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderHistory{
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("table_id")
    @Expose
    var tableId: Int = 0

    @SerializedName("waiter_id")
    @Expose
    var waiterId: Int = 0

    @SerializedName("isOpen")
    @Expose
    var isOpen: Int = 0

    @SerializedName("created_at")
    @Expose
    var orderDate: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedDate: String? = null

    @SerializedName("total")
    @Expose
    var orderTotal: Int = 0

}