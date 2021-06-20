package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.QuoteTypeResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.QuoteTypeEntity
import javax.inject.Inject

class QuoteTypeResponseToQuoteTypeEntityMapper @Inject constructor() :
    EntityMapper<QuoteTypeResponse, QuoteTypeEntity> {
    override fun mapToEntity(entity: QuoteTypeEntity): QuoteTypeResponse {
        return QuoteTypeResponse(
            entity.price,
            entity.volume24h,
            entity.percentChange1h,
            entity.percentChange24h,
            entity.percentChange7d,
            entity.percentChange30d,
            entity.percentChange60d,
            entity.percentChange90d,
            entity.coinMarketRank,
            entity.lastUpdated
        )
    }

    override fun mapFromEntity(entity: QuoteTypeResponse): QuoteTypeEntity {
        return QuoteTypeEntity(
            entity.price,
            entity.volume24h,
            entity.percentChange1h,
            entity.percentChange24h,
            entity.percentChange7d,
            entity.percentChange30d,
            entity.percentChange60d,
            entity.percentChange90d,
            entity.coinMarketRank,
            entity.lastUpdated
        )
    }
}