package com.baset.crypto.domain

import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.domain.entity.QuoteTypeEntity
import com.baset.crypto.domain.entity.params.*

fun createRemoteCryptocurrenciesParams(
    page: Int = 1,
    pageLimit: Int = 20,
    sortBy: CryptocurrencySortType = CryptocurrencySortType.NAME,
    sortDirection: SortDirection = SortDirection.ASCENDING,
    cryptocurrencyType: CryptocurrencyFilterType = CryptocurrencyFilterType.COINS,
    tagType: TagFilterType = TagFilterType.DEFI
): GetRemoteCryptocurrenciesParams {
    return GetRemoteCryptocurrenciesParams(
        page, pageLimit, sortBy, sortDirection, cryptocurrencyType, tagType
    )
}

fun createCryptocurrencyDetailEntity(
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

fun createCryptocurrencyEntity(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    coinMarketRank: Int = 5,
    quote: QuoteEntity = createQuoteEntity()
): CryptocurrencyEntity {
    return CryptocurrencyEntity(
        id,
        name,
        symbol, coinMarketRank, quote
    )
}

fun createQuoteEntity(type: QuoteTypeEntity = createQuoteTypeEntity()): QuoteEntity {
    return QuoteEntity(type)
}

fun createQuoteTypeEntity(
    price: Double = 9283.92,
    volume24h: Double = 715568.0000
): QuoteTypeEntity {
    return QuoteTypeEntity(
        price,
        volume24h,
    )
}