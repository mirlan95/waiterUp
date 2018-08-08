package com.example.mirlan.waiterup.api

import com.example.mirlan.waiterup.data.network.*
import kotlinx.coroutines.experimental.Deferred
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

    @GET("foods")
    fun getFoods(): Deferred<List<Food>>

    @GET("orders")
    fun getOrderHistory(@Query("waiter_id") waiterId:Int,
                        @Query("api_token") mToken:String,
                        @Query("is_open") isOpen:Int): Deferred<List<OrderHistory>>

    @GET("orders/{id}")
    fun getOrderItems(@Path("id")id: Int,
                      @Query("api_token") mToken:String,
                      @Query("waiter_id") waiterId: Int): Deferred<List<OrderItem>>

    @DELETE("orders/{id}")
    fun closeOrder(@Path("id") id: Int,
                   @Query("api_token")accessToken: String) : Deferred<Boolean>

    @POST("orders{id}")
    @FormUrlEncoded
    fun updateOrder(@Field("api_token")mToken: String,
                    @Field("waiter_id") waiterId: Int,
                    @Field("table_id") tableId: Int,
                    @FieldMap map:Map<Int,String>) : Deferred<OrderFood>

    @POST("print/{id}")
    fun printCheck(@Path("id") id:Int) : Deferred<Boolean>

    @PUT("orders{id}")
    @FormUrlEncoded
    fun addMoreOrder(@Field("api_token")mToken: String,
                     @FieldMap map:Map<Int,String>) : Deferred<ExtraOrder>

    @POST("orders")
    @FormUrlEncoded
    fun sendOrder(@Field("api_token") mToken: String,
                  @Field("waiter_id") waiterId: Int,
                  @Field("table_id") tableId: Int,
                  @Field("isExternal") isExternal:Int,
                  @Field("date") date:String,
                  @Field("foods") list: ArrayList<Test>) : Deferred<OrderBackStatus>


}