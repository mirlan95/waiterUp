package com.example.mirlan.waiterup.data.network

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Food() : Parcelable {
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

    var quantity: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        price = parcel.readInt()
        isHave = parcel.readInt()
        catId = parcel.readInt()
        quantity = parcel.readInt()
    }

    override fun toString(): String {
        // return super.toString()
        return "$name"
    }

    //"created_at": null,
    //"updated_at": null
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(isHave)
        parcel.writeInt(catId)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }

}