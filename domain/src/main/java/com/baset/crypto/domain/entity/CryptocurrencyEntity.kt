package com.baset.crypto.domain.entity

data class CryptocurrencyEntity(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val numberOfMarketPairs: Long,
    val dateAdded: String,
    val tags: List<String>,
    val maxSupply: Long? = null,
    val circulatingSupply: Long,
    val totalSupply: Long,
    val platform: PlatformEntity? = null,
    val coinMarketRank: Int,
    val lastUpdated: String,
    val quote: QuoteEntity
)
