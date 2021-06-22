package com.baset.crypto.data.source.remote

import com.baset.crypto.data.entity.CryptocurrenciesResponse
import com.baset.crypto.data.entity.CryptocurrencyDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cryptocurrency/listings/latest")
    suspend fun getCryptocurrencies(
        @Query("start") page: Int,
        @Query("limit") pageLimit: Int,
        @Query("sort") sortBy: String,
        @Query("sort_dir") sortDirection: String,
        @Query("cryptocurrency_type") cryptocurrencyType: String,
        @Query("tag") tagType: String
    ): CryptocurrenciesResponse

    @GET("cryptocurrency/info")
    suspend fun getCryptocurrencyDetail(id: Int): CryptocurrencyDetailsResponse
}