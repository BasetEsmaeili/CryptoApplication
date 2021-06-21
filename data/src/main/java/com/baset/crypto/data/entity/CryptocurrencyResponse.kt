package com.baset.crypto.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CryptocurrencyResponse(
    val id: Long,
    val name: String,
    val symbol: String,
    val coinMarketRank: Int,
    val quote: QuoteResponse
)
