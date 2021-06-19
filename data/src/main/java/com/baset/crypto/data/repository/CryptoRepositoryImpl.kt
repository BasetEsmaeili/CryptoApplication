package com.baset.crypto.data.repository

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.domain.repository.CryptoRepository
import com.baset.crypto.domain.source.CryptoLocalDataSource
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoLocalDataSource
) : CryptoRepository {
    override suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int
    ): Result<List<CryptocurrencyEntity?>> {
        return remoteDataSource.getCryptocurrencies(page, pageLimit)
    }

    override fun getCryptocurrencies(
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Flow<List<CryptocurrencyEntity?>?> {
        return localDataSource.getCryptocurrencies(
            sortBy,
            sortDirection,
            cryptocurrencyType,
            tagType
        )
    }

    override suspend fun insertCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity?>) {
        localDataSource.insertCryptocurrencies(cryptocurrencies)
    }
}