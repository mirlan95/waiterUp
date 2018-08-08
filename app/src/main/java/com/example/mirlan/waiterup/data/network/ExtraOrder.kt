package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExtraOrder {
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

    @SerializedName("total")
    @Expose
    var total: Int = 0


}


