package com.baset.crypto.data.utils.network

import com.baset.crypto.data.utils.exceptions.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkCheckInterceptor @Inject constructor(private val networkManager: NetworkManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkManager.isNetworkAvailable())
            throw NoConnectionException("No Intent Connection")
        return chain.proceed(chain.request())
    }
}