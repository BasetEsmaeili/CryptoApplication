package com.baset.crypto.data.source.remote

import com.baset.crypto.data.mapper.CryptocurrenciesResponseToCryptocurrencyEntityListMapper
import com.baset.crypto.data.mapper.CryptocurrencyDetailsResponseToCryptocurrencyDetailEntityMapper
import com.baset.crypto.data.utils.network.ResponseHandler
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import javax.inject.Inject

class CryptoRetrofitImplDataSource @Inject constructor(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler,
    private val cryptocurrenciesMapper: CryptocurrenciesResponseToCryptocurrencyEntityListMapper,
    private val cryptoDetailMapper: CryptocurrencyDetailsResponseToCryptocurrencyDetailEntityMapper
) :
    CryptoRemoteDataSource {
    override suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int,
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Result<List<CryptocurrencyEntity?>> {
        return try {
            val response = apiService.getCryptocurrencies(
                page,
                pageLimit,
                sortBy.rawValue,
                sortDirection.rawValue,
                cryptocurrencyType.rawValue,
                tagType.rawValue
            )
            Result.success(cryptocurrenciesMapper.mapFromEntity(response))
        } catch (error: Exception) {
            responseHandler.getErrorResult(error)
        }
    }

    override suspend fun getCryptocurrencyDetail(id: Int): Result<CryptocurrencyDetailEntity> {
        return try {
            val response = apiService.getCryptocurrencyDetail(id)
            Result.success(cryptoDetailMapper.mapFromEntity(response))
        } catch (error: Exception) {
            responseHandler.getErrorResult(error)
        }
    }

}