package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderBackStatus {

    @SerializedName("waiter_id")
    @Expose
    var waiterId: Int = 0

    @SerializedName("table_id")
    @Expose
    var tableId: Int = 0

    @SerializedName("updated_at")
    @Expose
    var updatedDate: String? = null

    @SerializedName("created_at")
    @Expose
    var createDate: String? = null


    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("total")
    @Expose
    var mTotal: Int = 0
    @SerializedName("foods")
    @Expose
    var foods: Int = 0

}