package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Result

interface CryptoRemoteDataSource {
    suspend fun getCryptocurrencies(
        page: Int,
        pageLimit: Int,
    ): Result<List<CryptocurrencyEntity?>>
}