package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TOKEN_ADDRESS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformResponse(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerialName(TOKEN_ADDRESS)
    val tokenAddress: String
)