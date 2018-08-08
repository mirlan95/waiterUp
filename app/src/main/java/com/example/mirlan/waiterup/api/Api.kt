package com.example.mirlan.waiterup.api

import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.Constants.BASE_URL
import com.example.mirlan.waiterup.view.LoginActivity.Companion.applicationContext
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
       // private val BASE_URL = SaveSharedPreference.getAccessToken(applicationContext())

        fun provideApi(): ApiInterface {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient(provideLoggingInterceptor()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory()) // https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter
                    .build()
                    .create(ApiInterface::class.java)
        }

        private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            val b = OkHttpClient.Builder()
            b.addInterceptor(interceptor)
            return b.build()
        }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }
    }
