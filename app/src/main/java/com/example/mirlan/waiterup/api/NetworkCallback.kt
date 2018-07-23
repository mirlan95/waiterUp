package com.example.mirlan.waiterup.api

class NetworkCallback<T> {
    var success: ((T) -> Unit) ?= null
    var error: ((Throwable)-> Unit) ?= null
}