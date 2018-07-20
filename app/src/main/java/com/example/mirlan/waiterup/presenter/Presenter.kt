package com.example.mirlan.waiterup.presenter

interface Presenter<T> {
    fun onResume()
    fun onDestroy()
    fun onStart()
    fun onError(string: String)
    fun onCompleted()
    fun showProgress()
    fun hideProgress()
}