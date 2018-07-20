package com.example.mirlan.waiterup.api

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Network private constructor() {
    val result: Api

    init {
        val httpClient = OkHttpClient()
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .client(httpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https:")
                .build()
        result = retrofit.create(Api::class.java)

    }

    companion object {
        private var instance: Network? = null
        val `interface`: Api
            @Synchronized get() {
                if (instance == null)
                    instance = Network()
                return instance!!.result
            }
    }
}
