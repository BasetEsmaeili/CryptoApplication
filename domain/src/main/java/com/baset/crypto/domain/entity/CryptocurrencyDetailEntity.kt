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
    val dateAdded: String,
    val twitterUsername: String
)