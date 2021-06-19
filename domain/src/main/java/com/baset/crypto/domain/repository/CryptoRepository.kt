package com.baset.crypto.domain.repository

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int,
    ): Result<List<CryptocurrencyEntity?>>

    fun getCryptocurrencies(
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Flow<List<CryptocurrencyEntity?>?>

    suspend fun insertCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity?>)
}