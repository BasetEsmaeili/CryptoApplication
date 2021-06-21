package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.LAST_UPDATED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_1H
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_24H
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_30D
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_60D
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_7D
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_90D
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.QUOTE_COIN_MARKET_RANK
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.VOLUME_24H
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteTypeResponse(
    val price: Double,
    @SerialName(PERCENT_CHANGE_24H)
    val percentChange24h: Double
)
