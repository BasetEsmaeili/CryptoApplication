package com.baset.crypto.data.utils.network

import com.baset.crypto.data.BuildConfig
import com.baset.crypto.data.common.DataConstants.Authentication.KEY_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        req = req.newBuilder().addHeader(KEY_API_KEY,BuildConfig.API_KEY).build()
        return chain.proceed(req)
    }
}