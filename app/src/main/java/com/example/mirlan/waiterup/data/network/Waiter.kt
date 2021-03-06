package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Waiter {

    @SerializedName("auth")
    @Expose
    var auth: Boolean? = false

    @SerializedName("api_token")
    @Expose
    var mToken: String? = null
}