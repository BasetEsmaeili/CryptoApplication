package com.baset.crypto.data

import com.baset.crypto.data.entity.db.CryptocurrencyDetailEntity
import com.baset.crypto.data.entity.db.CryptocurrencyEntity

fun createDbCryptocurrencyEntity(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    price: Double = 9283.92,
    volume24h: Double = 715568.0000,
    marketCap: Double = 232276569366.00534
): CryptocurrencyEntity {
    return CryptocurrencyEntity(
        id, name, symbol,marketCap , price, volume24h
    )
}

fun createDbCryptocurrencyDetailEntity(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    category: String = "coin",
    description: String = "Bitcoin (BTC) is a cryptocurrency .",
    slug: String = "bitcoin",
    logo: String = "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png",
    subreddit: String = "bitcoin",
    notice: String = "",
    dateAdded: String = "2013-04-28T00:00:00.000Z",
    twitterUsername: String = ""
): CryptocurrencyDetailEntity {
    return CryptocurrencyDetailEntity(
        id,
        name,
        symbol,
        category,
        description,
        slug,
        logo,
        subreddit,
        notice,
        dateAdded,
        twitterUsername
    )
}