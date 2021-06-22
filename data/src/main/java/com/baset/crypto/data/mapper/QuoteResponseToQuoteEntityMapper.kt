package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.QuoteResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.QuoteEntity
import javax.inject.Inject

class QuoteResponseToQuoteEntityMapper @Inject constructor(
    private val quoteTypeMapper: QuoteTypeResponseToQuoteTypeEntityMapper
) :
    EntityMapper<QuoteResponse, QuoteEntity> {
    override fun mapToEntity(entity: QuoteEntity): QuoteResponse {
        return QuoteResponse(
            quoteTypeMapper.mapToEntity(entity.usd)
        )
    }

    override fun mapFromEntity(entity: QuoteResponse): QuoteEntity {
        return QuoteEntity(
            quoteTypeMapper.mapFromEntity(entity.usd)
        )
    }
}