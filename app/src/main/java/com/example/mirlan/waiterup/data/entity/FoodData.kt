package com.example.mirlan.waiterup.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "food_list")
data class FoodData(@ColumnInfo(name="name") var name: String,
                    @ColumnInfo(name="price") var price: Int,
                    @ColumnInfo(name="category_id") var catId:Int,
                    @ColumnInfo(name="isHave") var isHave: Int,
                    @ColumnInfo(name="quantity") var quantity: Int,
                    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int = 0)