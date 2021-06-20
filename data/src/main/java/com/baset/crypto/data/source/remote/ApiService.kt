package com.baset.crypto.data.source.remote

import com.baset.crypto.data.entity.CryptocurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cryptocurrency/listings/latest")
    suspend fun getCryptocurrencies(
        @Query("start") page: Int,
        @Query("limit") pageLimit: Int
    ): CryptocurrenciesResponse
}