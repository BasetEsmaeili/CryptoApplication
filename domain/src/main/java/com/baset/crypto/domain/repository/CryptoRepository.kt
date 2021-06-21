package com.baset.crypto.domain.repository

import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
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
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Result<List<CryptocurrencyEntity?>>

    fun getCryptocurrencies(): Flow<List<CryptocurrencyEntity?>?>

    suspend fun getRemoteCryptocurrencyDetail(id: Int): Result<CryptocurrencyDetailEntity>

    fun getLocalCryptocurrencyDetail(id: Int): Flow<CryptocurrencyDetailEntity?>
}