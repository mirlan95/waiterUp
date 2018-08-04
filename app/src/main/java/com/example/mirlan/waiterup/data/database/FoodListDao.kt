package com.example.mirlan.waiterup.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.mirlan.waiterup.data.entity.FoodData
import com.example.mirlan.waiterup.data.network.Food

@Dao
interface FoodListDao {

    @Insert
    fun insertAll (foodList: List<FoodData>)

    @Query("SELECT * FROM food_list")
    fun getAll(): List<FoodData>

    @Query("DELETE from food_list")
    fun deleteAll()

    @Query("SELECT * FROM food_list WHERE category_id = :sourceId")
    fun findBySourceId(sourceId: Int): List<FoodData>
}