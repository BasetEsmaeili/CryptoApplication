package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.CIRCULATING_SUPPLY
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.COIN_MARKET_RANK
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.DATE_ADDED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.LAST_UPDATED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.MAX_SUPPLY
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.NUMBER_OF_MARKET_PAIRS
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TOTAL_SUPPLY
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptocurrencyResponse(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerialName(NUMBER_OF_MARKET_PAIRS)
    val numberOfMarketPairs: Long,
    @SerialName(DATE_ADDED)
    val dateAdded: String,
    val tags: List<String>,
    @SerialName(MAX_SUPPLY)
    val maxSupply: Long? = null,
    @SerialName(CIRCULATING_SUPPLY)
    val circulatingSupply: Long,
    @SerialName(TOTAL_SUPPLY)
    val totalSupply: Long,
    val platform: PlatformResponse? = null,
    @SerialName(COIN_MARKET_RANK)
    val coinMarketRank: Int,
    @SerialName(LAST_UPDATED)
    val lastUpdated: String,
    val quote: QuoteResponse
)
