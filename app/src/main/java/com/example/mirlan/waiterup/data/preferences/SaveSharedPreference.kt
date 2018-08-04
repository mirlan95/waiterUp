package com.example.mirlan.waiterup.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SaveSharedPreference{

    private const val PREF_USER_NAME = "username"
    private const val PREF_USER_TOKEN = "password"
    private const val PREF_USER_ID = "id"
    private const val PREF_API_ADDRESS = "http://198.162.254.0:8080"

    private fun getSharedPreferences(ctx: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun setUserId(ctx: Context,userId: Int){
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_ID, userId.toString())
        editor.apply()
    }

    fun getUserId(ctx: Context): String {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "")
    }

    fun setUserName(ctx: Context, userName: String) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_NAME, userName)
        editor.apply()
    }

    fun getUserName(ctx: Context): String {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "")
    }

    fun setAccessToken(ctx: Context, accessToken: String) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(ctx: Context): String {
        return getSharedPreferences(ctx).getString(PREF_USER_TOKEN, "").toString()
    }
    fun setApiAddress(ctx: Context, apiAddress:String){
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_API_ADDRESS,apiAddress)
        editor.apply()
    }
    fun getApiAddress(ctx: Context):String{
        return getSharedPreferences(ctx).getString(PREF_API_ADDRESS,"")
    }
}
