package com.example.mirlan.waiterup.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.mirlan.waiterup.data.entity.FoodData

@Database(entities = [(FoodData::class)], version = 1)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun foodDataDao(): FoodListDao

    companion object {
        private var INSTANCE: FoodDataBase? = null

        fun getInstance(context: Context): FoodDataBase? {
            if (INSTANCE == null) {
                synchronized(FoodDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FoodDataBase::class.java, "food.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
