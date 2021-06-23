package com.baset.crypto.trader.mapper

import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.trader.entity.Cryptocurrency
import javax.inject.Inject

class CryptocurrencyToCryptocurrencyEntityMapper @Inject constructor(private val quoteMapper: QuoteToQuoteEntityMapper) :
    EntityMapper<Cryptocurrency, CryptocurrencyEntity> {
    override fun mapToEntity(entity: CryptocurrencyEntity): Cryptocurrency {
        return Cryptocurrency(
            entity.id,
            entity.name,
            entity.symbol,
            quoteMapper.mapToEntity(entity.quote)
        )
    }

    override fun mapFromEntity(entity: Cryptocurrency): CryptocurrencyEntity {
        return CryptocurrencyEntity(
            entity.id,
            entity.name,
            entity.symbol,
            quoteMapper.mapFromEntity(entity.quote)
        )
    }
}