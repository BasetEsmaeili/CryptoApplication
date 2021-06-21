package com.baset.crypto.domain

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.domain.entity.QuoteTypeEntity

fun createCryptocurrencyEntity(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    coinMarketRank: Int = 5,
    quote: QuoteEntity = createQuoteEntity()
): CryptocurrencyEntity {
    return CryptocurrencyEntity(
        id,
        name,
        symbol, coinMarketRank, quote
    )
}

fun createQuoteEntity(type: QuoteTypeEntity = createQuoteTypeEntity()): QuoteEntity {
    return QuoteEntity(type)
}

fun createQuoteTypeEntity(
    price: Double = 9283.92,
    volume24h: Double = 715568.0000
): QuoteTypeEntity {
    return QuoteTypeEntity(
        price,
        volume24h,
    )
}