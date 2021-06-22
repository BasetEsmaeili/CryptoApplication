package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_24H
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteTypeResponse(
    val price: Double,
    @SerialName(PERCENT_CHANGE_24H)
    val percentChange24h: Double
)
