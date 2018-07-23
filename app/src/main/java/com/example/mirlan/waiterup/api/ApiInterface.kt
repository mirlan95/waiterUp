package com.example.mirlan.waiterup.api

import android.database.Observable
import com.example.mirlan.waiterup.data.network.Categories
import com.example.mirlan.waiterup.data.network.User
import com.example.mirlan.waiterup.data.network.Waiter
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.view.LoginActivity.Companion.applicationContext
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface {
    @GET("users")
    fun getWaiters() : Deferred<List<User>>

    @POST("login")
    @FormUrlEncoded
    fun logIn(@Field("id") id: Int,
              @Field("password") mPassword: String):Deferred<Waiter>
    @GET("categories")
    fun getCategories(): Deferred<List<Categories>>
            /*  @GET("/orders{id}")
    fun getQuestions(@Path("id")id:Int,
                     @Query("api_token")examId: Int,
                     @Query("waiter_id")):Observable<>





    @POST("/result")
    @FormUrlEncoded
    fun sendResult(@Field("result") id: Int,
                   @Field("student_id") stuId:Int,
                   @Field("exam_id")exId:Int,
                   @Field("examData") exDate: String ) : Observable<StatusBack>
                   */

    companion object Factory{

        fun create(): ApiInterface{
            val requestInterface = Retrofit.Builder()
                    .baseUrl(SaveSharedPreference.getApiAddress(applicationContext()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return requestInterface.create(ApiInterface::class.java)
        }

    }

}