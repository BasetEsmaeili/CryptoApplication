package com.baset.crypto.trader.mapper

import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.trader.entity.Quote
import javax.inject.Inject

class QuoteToQuoteEntityMapper @Inject constructor(private val quoteTypeMapper: QuoteTypeToQuoteTypeEntityMapper) :
    EntityMapper<Quote, QuoteEntity> {
    override fun mapToEntity(entity: QuoteEntity): Quote {
        return Quote(
            quoteTypeMapper.mapToEntity(entity.usd)
        )
    }

    override fun mapFromEntity(entity: Quote): QuoteEntity {
        return QuoteEntity(
            quoteTypeMapper.mapFromEntity(entity.usd)
        )
    }
}