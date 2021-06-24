package com.baset.crypto.trader.mapper

import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.trader.entity.Cryptocurrency
import javax.inject.Inject

class CryptocurrencyListToCryptocurrencyEntityMapper @Inject constructor(
    private val cryptocurrencyMapper: CryptocurrencyToCryptocurrencyEntityMapper
) :
    EntityMapper<List<Cryptocurrency?>?, List<CryptocurrencyEntity?>?> {
    override fun mapToEntity(entity: List<CryptocurrencyEntity?>?): List<Cryptocurrency?> {
        return entity?.filterNotNull()?.map { cryptocurrencyMapper.mapToEntity(it) } ?: listOf()
    }

    override fun mapFromEntity(entity: List<Cryptocurrency?>?): List<CryptocurrencyEntity?> {
        return entity?.filterNotNull()?.map { cryptocurrencyMapper.mapFromEntity(it) } ?: listOf()
    }
}