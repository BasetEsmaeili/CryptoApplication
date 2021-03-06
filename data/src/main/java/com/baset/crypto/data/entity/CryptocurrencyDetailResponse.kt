package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.DATE_ADDED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TWITTER_USER_NAME
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptocurrencyDetailResponse(
    val id: Long,
    val name: String,
    val symbol: String,
    val category: String,
    val description: String,
    val slug: String,
    val logo: String,
    val subreddit: String,
    val notice: String,
    @SerialName(DATE_ADDED)
    val dateAdded: String,
    @SerialName(TWITTER_USER_NAME)
    val twitterUsername: String,
)