package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.MESSAGE_BOARD
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.SOURCE_CODE
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TECHNICAL_DOC
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrlListResponse(
    val website: List<String>,
    val twitter: List<String>,
    @SerialName(MESSAGE_BOARD)
    val messageBoard: List<String>,
    val chat: List<String>,
    val explorer: List<String>,
    val reddit: List<String>,
    @SerialName(TECHNICAL_DOC)
    val technicalDoc: List<String>,
    @SerialName(SOURCE_CODE)
    val sourceCode: List<String>,
    val announcement: List<String>
)
