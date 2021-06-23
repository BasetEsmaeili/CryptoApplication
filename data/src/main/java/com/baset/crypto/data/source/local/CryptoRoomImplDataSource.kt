package com.baset.crypto.data.source.local

import com.baset.crypto.data.mapper.DbCryptocurrencyDetailEntityToCryptocurrencyDeatilEntityMapper
import com.baset.crypto.data.mapper.DbCryptocurrencyEntityListToCryptocurrencyEntityListMapper
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.source.CryptoLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CryptoRoomImplDataSource @Inject constructor(
    private val cryptoDAO: CryptoDAO,
    private val cryptocurrencyMapper: DbCryptocurrencyEntityListToCryptocurrencyEntityListMapper,
    private val cryptoDetailMapper: DbCryptocurrencyDetailEntityToCryptocurrencyDeatilEntityMapper
) : CryptoLocalDataSource {
    override suspend fun insertCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity?>) {
        cryptoDAO.insertOrUpdateCryptocurrencies(cryptocurrencyMapper.mapToEntity(cryptocurrencies))
    }

    override fun getCryptocurrencies(): Flow<List<CryptocurrencyEntity?>?> {
        return cryptoDAO.getCryptocurrencies().map { cryptocurrencyMapper.mapFromEntity(it) }
    }

    override suspend fun insertCryptocurrencyDetail(cryptocurrencyDetail: CryptocurrencyDetailEntity) {
        cryptoDAO.insertOrUpdateCryptocurrencyDetails(cryptoDetailMapper.mapToEntity(cryptocurrencyDetail))
    }

    override fun getCryptocurrencyDetail(id: Int): Flow<CryptocurrencyDetailEntity?> {
        return cryptoDAO.getCryptocurrencyDetail(id).map { cryptoDetailMapper.mapFromEntity(it) }
    }

    override suspend fun clearCryptocurrencies() {
        cryptoDAO.clearCryptocurrencies()
    }
}