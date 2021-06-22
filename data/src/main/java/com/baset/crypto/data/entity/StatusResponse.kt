package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.CREDIT_COUNT
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.ERROR_CODE
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.ERROR_MESSAGE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val timestamp: String,
    @SerialName(ERROR_CODE)
    val errorCode: Int,
    @SerialName(ERROR_MESSAGE)
    val errorMessage: String? = null,
    val elapsed: Long,
    @SerialName(CREDIT_COUNT)
    val creditCount: Long
)