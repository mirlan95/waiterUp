package com.example.mirlan.waiterup.presenter

interface Presenter<T> {
    fun onResume()
    fun onDestroy()
    fun onStart()
    fun onError(s: String)
    fun onCompleted()
    fun showProgress()
    fun hideProgress()
    fun onFinished(list: List<T>)
}