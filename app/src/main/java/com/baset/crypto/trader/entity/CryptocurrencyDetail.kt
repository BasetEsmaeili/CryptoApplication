package com.baset.crypto.trader.entity

data class CryptocurrencyDetail(
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
