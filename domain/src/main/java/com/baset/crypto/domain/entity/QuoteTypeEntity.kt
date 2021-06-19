package com.baset.crypto.domain.entity

data class QuoteTypeEntity(
    val price: Double,
    val volume24h: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange90d: Double,
    val coinMarketRank: Double,
    val lastUpdated: String
)
