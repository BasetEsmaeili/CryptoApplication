package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.QUOTE_COIN_MARKET_RANK
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptocurrencyResponse(
    val id: Long,
    val name: String,
    val symbol: String,
    @SerialName(QUOTE_COIN_MARKET_RANK)
    val coinMarketRank: Int,
    val quote: QuoteResponse
)
