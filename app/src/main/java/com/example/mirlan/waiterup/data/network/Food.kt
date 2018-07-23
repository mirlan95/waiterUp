package com.example.mirlan.waiterup.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Food {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: Int = 0

    @SerializedName("isHave")
    @Expose
    var isHave: Int = 0

    @SerializedName("category_id")
    @Expose
    var catId: Int = 0

    override fun toString(): String {
        // return super.toString()
        return "$name"
    }

    //"created_at": null,
    //"updated_at": null
}