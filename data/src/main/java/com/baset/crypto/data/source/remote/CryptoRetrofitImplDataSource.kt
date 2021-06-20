package com.baset.crypto.data.source.remote

import com.baset.crypto.data.mapper.CryptocurrenciesResponseToCryptocurrencyEntityListMapper
import com.baset.crypto.data.utils.network.ResponseHandler
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import javax.inject.Inject

class CryptoRetrofitImplDataSource @Inject constructor(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler,
    private val mapper: CryptocurrenciesResponseToCryptocurrencyEntityListMapper
) :
    CryptoRemoteDataSource {
    override suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int
    ): Result<List<CryptocurrencyEntity?>> {
        return try {
            val response = apiService.getCryptocurrencies(page, pageLimit)
            Result.success(mapper.mapFromEntity(response))
        } catch (error: Exception) {
            responseHandler.getErrorResult(error)
        }
    }

}