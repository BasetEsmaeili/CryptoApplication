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
            entity.percentChange24h
        )
    }

    override fun mapFromEntity(entity: QuoteTypeResponse): QuoteTypeEntity {
        return QuoteTypeEntity(
            entity.price,
            entity.percentChange24h,
        )
    }
}