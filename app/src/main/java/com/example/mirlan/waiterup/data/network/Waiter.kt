package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Waiter {
    @SerializedName("id")
    @Expose
    var mWaiterId: Int = 0

    @SerializedName("name")
    @Expose
    var mName: String? = null

    @SerializedName("api_token")
    @Expose
    var mToken: String? = null
}