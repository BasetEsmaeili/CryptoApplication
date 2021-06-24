package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.CryptocurrencyResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import javax.inject.Inject

class CryptocurrencyResponseToCryptocurrencyEntityMapper @Inject constructor(
    private val quoteMapper: QuoteResponseToQuoteEntityMapper
) : EntityMapper<CryptocurrencyResponse, CryptocurrencyEntity> {
    override fun mapToEntity(entity: CryptocurrencyEntity): CryptocurrencyResponse {
        return CryptocurrencyResponse(
            entity.id,
            entity.name,
            entity.symbol,
            quoteMapper.mapToEntity(entity.quote)
        )
    }

    override fun mapFromEntity(entity: CryptocurrencyResponse): CryptocurrencyEntity {
        return CryptocurrencyEntity(
            entity.id,
            entity.name,
            entity.symbol,
            quoteMapper.mapFromEntity(entity.quote)
        )
    }
}