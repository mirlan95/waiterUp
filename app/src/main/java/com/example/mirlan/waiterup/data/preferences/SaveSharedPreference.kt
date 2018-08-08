package com.example.mirlan.waiterup.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.mirlan.waiterup.utils.Constants
import com.example.mirlan.waiterup.utils.Constants.PREF_API_ADDRESS
import com.example.mirlan.waiterup.utils.Constants.PREF_USER_ID
import com.example.mirlan.waiterup.utils.Constants.PREF_USER_NAME
import com.example.mirlan.waiterup.utils.Constants.PREF_USER_TOKEN

object SaveSharedPreference{

    private fun getSharedPreferences(ctx: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun setUserId(ctx: Context,userId: Int){
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(Constants.PREF_USER_ID, userId.toString())
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
