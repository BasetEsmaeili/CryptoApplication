package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Resource
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType

interface CryptoRemoteDataSource {
    suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int,
        sortBy: CryptocurrencySortType,
        sortDirection: SortDirection,
        cryptocurrencyType: CryptocurrencyFilterType,
        tagType: TagFilterType
    ): Resource<List<CryptocurrencyEntity>>
}