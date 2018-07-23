package com.example.mirlan.waiterup.api

import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException


object Network {
    fun defaultError(t: Throwable) {
        t.printStackTrace()
    }

    fun <T> request(call: Deferred<T>, callback: NetworkCallback<T>) {
        request(call, callback.success, callback.error)
    }

    private fun <T> request(call: Deferred<T>, onSuccess: ((T) -> Unit)?, onError: ((Throwable) -> Unit)?) {
        launch(UI) {
            try {
                onSuccess?.let {
                    onSuccess(call.await())
                }
            } catch (httpException: HttpException) {
                // a non-2XX response was received
                defaultError(httpException)
            } catch (t: Throwable) {
                // a networking or data conversion error
                onError?.let {
                    onError(t)
                }
            }
        }
    }
}