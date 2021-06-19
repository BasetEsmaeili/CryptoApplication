package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import kotlinx.coroutines.flow.Flow

interface CryptoLocalDataSource {

    suspend fun insertCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity?>)

    fun getCryptocurrencies(
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Flow<List<CryptocurrencyEntity?>?>
}