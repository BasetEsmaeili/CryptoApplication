package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Resource

interface CryptoRemoteDataSource {
    suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int,
    ): Resource<List<CryptocurrencyEntity?>>
}