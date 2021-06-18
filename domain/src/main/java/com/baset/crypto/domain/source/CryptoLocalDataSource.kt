package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Resource
import kotlinx.coroutines.flow.Flow

interface CryptoLocalDataSource {
    fun getCryptocurrencies(): Flow<Resource<List<CryptocurrencyEntity>>>
}