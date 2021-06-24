package com.baset.crypto.trader.mapper

import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.QuoteTypeEntity
import com.baset.crypto.trader.entity.QuoteType
import javax.inject.Inject

class QuoteTypeToQuoteTypeEntityMapper @Inject constructor() : EntityMapper<QuoteType, QuoteTypeEntity> {
    override fun mapToEntity(entity: QuoteTypeEntity): QuoteType {
        return QuoteType(
            entity.price,
            entity.percentChange24h,
            entity.marketCap
        )
    }

    override fun mapFromEntity(entity: QuoteType): QuoteTypeEntity {
        return QuoteTypeEntity(
            entity.price,
            entity.percentChange24h,
            entity.marketCap
        )
    }
}