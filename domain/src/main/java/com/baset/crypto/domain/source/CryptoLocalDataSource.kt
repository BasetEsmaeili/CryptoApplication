package com.baset.crypto.domain.source

import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CryptoLocalDataSource {

    suspend fun insertCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity?>)

    fun getCryptocurrencies(): Flow<List<CryptocurrencyEntity?>?>

    suspend fun insertCryptocurrencyDetail(cryptocurrencyDetail: CryptocurrencyDetailEntity)

    fun getCryptocurrencyDetail(id: Int): Flow<CryptocurrencyDetailEntity?>

    suspend fun clearCryptocurrencies()
}