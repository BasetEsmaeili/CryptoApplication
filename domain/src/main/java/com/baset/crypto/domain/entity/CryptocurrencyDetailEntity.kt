package com.baset.crypto.domain.entity

data class CryptocurrencyDetailEntity(
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
    val tagNames: List<String>,
    val tagGroups: List<String>,
    val urls: UrlListEntity,
    val platform: PlatformEntity? = null,
    val dateAdded: String,
    val twitterUsername: String,
    val isHidden: Boolean
)