package com.baset.crypto.data.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import javax.inject.Inject

class NetworkManager @Inject constructor(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun registerNetworkCallback(callback: ConnectivityManager.NetworkCallback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            connectivityManager.registerDefaultNetworkCallback(callback)
        else {
            connectivityManager.registerNetworkCallback(buildNetworkRequest(), callback)
        }
    }

    fun unRegisterNetworkCallback(callback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(callback)
    }

    private fun buildNetworkRequest(): NetworkRequest {
        return NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
    }


    fun isNetworkAvailable(): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.let {
                result = when (it.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }
}