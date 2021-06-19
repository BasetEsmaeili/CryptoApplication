package com.baset.crypto.domain

import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.PlatformEntity
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.domain.entity.QuoteTypeEntity

fun createCryptocurrencyEntity(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    slug: String = "bitcoin",
    numberOfMarketPairs: Long = 500,
    dateAdded: String = "2013-04-28T00:00:00.000Z",
    tags: List<String> = listOf("mineable"),
    maxSupply: Long? = 21000000,
    circulatingSupply: Long = 16950100,
    totalSupply: Long = 16950100,
    platform: PlatformEntity? = null,
    coinMarketRank: Int = 5,
    lastUpdated: String = "2018-06-02T22:51:28.209Z",
    quote: QuoteEntity = createQuoteEntity()
): CryptocurrencyEntity {
    return CryptocurrencyEntity(
        id,
        name,
        symbol,
        slug,
        numberOfMarketPairs,
        dateAdded,
        tags,
        maxSupply,
        circulatingSupply,
        totalSupply,
        platform,
        coinMarketRank,
        lastUpdated,
        quote
    )
}

fun createQuoteEntity(type: QuoteTypeEntity = createQuoteTypeEntity()): QuoteEntity {
    return QuoteEntity(type)
}

fun createQuoteTypeEntity(
    price: Double = 9283.92,
    volume24h: Double = 715568.0000,
    percentChange1h: Double = -0.152774,
    percentChange24h: Double = 0.518894,
    percentChange7d: Double = 0.986573,
    percentChange30d: Double = -7.14301848,
    percentChange60d: Double = 4.97958927,
    percentChange90d: Double = 21.33946819,
    coinMarketRank: Double = 262040732349.9464,
    lastUpdated: String = "2018-06-02T22:51:28.209Z"
): QuoteTypeEntity {
    return QuoteTypeEntity(
        price,
        volume24h,
        percentChange1h,
        percentChange24h,
        percentChange7d,
        percentChange30d,
        percentChange60d,
        percentChange90d,
        coinMarketRank,
        lastUpdated
    )
}