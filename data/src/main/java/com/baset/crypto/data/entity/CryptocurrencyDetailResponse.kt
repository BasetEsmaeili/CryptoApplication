package com.baset.crypto.data.entity

import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.DATE_ADDED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.IS_HIDDEN
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TAG_GROUPS
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TAG_NAMES
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TWITTER_USER_NAME
import com.baset.crypto.data.utils.ByteToBooleanSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
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
    val tags: List<String>,
    @SerialName(TAG_NAMES)
    val tagNames: List<String>,
    @SerialName(TAG_GROUPS)
    val tagGroups: List<String>,
    val urls: UrlListResponse,
    val platform: PlatformResponse? = null,
    @SerialName(DATE_ADDED)
    val dateAdded: String,
    @SerialName(TWITTER_USER_NAME)
    val twitterUsername: String,
    @SerialName(IS_HIDDEN)
    @Serializable(with = ByteToBooleanSerializer::class)
    val isHidden: Boolean
)