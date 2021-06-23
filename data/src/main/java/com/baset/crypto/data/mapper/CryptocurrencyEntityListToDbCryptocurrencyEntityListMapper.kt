package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.db.CryptocurrencyEntity
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.domain.entity.QuoteTypeEntity
import javax.inject.Inject

class CryptocurrencyEntityListToDbCryptocurrencyEntityListMapper @Inject constructor() :
    EntityMapper<List<CryptocurrencyEntity?>, List<com.baset.crypto.domain.entity.CryptocurrencyEntity?>> {
    override fun mapToEntity(entity: List<com.baset.crypto.domain.entity.CryptocurrencyEntity?>): List<CryptocurrencyEntity> {
        return entity.filterNotNull().map {
            CryptocurrencyEntity(
                it.id,
                it.name,
                it.symbol,
                it.quote.usd.marketCap,
                it.quote.usd.price,
                it.quote.usd.percentChange24h
            )
        }
    }

    override fun mapFromEntity(entity: List<CryptocurrencyEntity?>): List<com.baset.crypto.domain.entity.CryptocurrencyEntity> {
        return entity.filterNotNull().map {
            com.baset.crypto.domain.entity.CryptocurrencyEntity(
                it.id,
                it.name,
                it.symbol,
                QuoteEntity(QuoteTypeEntity(it.usdPrice, it.percentChange24h,it.marketCap))
            )
        }
    }

}