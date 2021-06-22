package com.baset.crypto.data.repository

import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.Status
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
        pageLimit: Int,
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Result<List<CryptocurrencyEntity?>> {
        val result = remoteDataSource.getCryptocurrencies(
            page,
            pageLimit,
            sortBy,
            sortDirection,
            cryptocurrencyType,
            tagType
        )
        if (result.status == Status.SUCCESS)
            result.data?.let {
                localDataSource.insertCryptocurrencies(it)
            }
        return result
    }

    override fun getCryptocurrencies(): Flow<List<CryptocurrencyEntity?>?> {
        return localDataSource.getCryptocurrencies()
    }

    override suspend fun getRemoteCryptocurrencyDetail(id: Int): Result<CryptocurrencyDetailEntity> {
        val result = remoteDataSource.getCryptocurrencyDetail(id)
        if (result.status == Status.SUCCESS)
            result.data?.let {
                localDataSource.insertCryptocurrencyDetail(it)
            }
        return result
    }

    override fun getLocalCryptocurrencyDetail(id: Int): Flow<CryptocurrencyDetailEntity?> {
        return localDataSource.getCryptocurrencyDetail(id)
    }

    override suspend fun clearCryptocurrencies() {
        localDataSource.clearCryptocurrencies()
    }
}