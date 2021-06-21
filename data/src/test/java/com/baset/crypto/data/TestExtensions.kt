package com.baset.crypto.data

import com.baset.crypto.data.entity.*
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.ErrorCode
import com.baset.crypto.domain.entity.QuoteEntity
import com.baset.crypto.domain.entity.QuoteTypeEntity

fun createCryptocurrencyDetailsResponse(
    status: StatusResponse = createStatusResponse(),
    detail: CryptocurrencyDetailResponse = createCryptocurrencyDetailResponse()
): CryptocurrencyDetailsResponse {
    return CryptocurrencyDetailsResponse(
        status, detail
    )
}

fun createCryptocurrencyDetailResponse(
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
): CryptocurrencyDetailResponse {
    return CryptocurrencyDetailResponse(
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

fun createCryptocurrenciesResponse(
    status: StatusResponse = createStatusResponse(),
    cryptocurrencies: List<CryptocurrencyResponse> = createCryptocurrencyResponseList()
): CryptocurrenciesResponse {
    return CryptocurrenciesResponse(
        status, cryptocurrencies
    )
}

fun createCryptocurrencyResponseList(): List<CryptocurrencyResponse> {
    return listOf(createCryptocurrencyResponse(), createCryptocurrencyResponse())
}

fun createStatusResponse(
    timestamp: String = "2021-06-20T18:36:12.315Z",
    errorCode: Int = ErrorCode.NO_ERROR.numberValue,
    errorMessage: String? = null,
    elapsed: Long = 12,
    creditCount: Long = 3
): StatusResponse {
    return StatusResponse(
        timestamp, errorCode, errorMessage, elapsed, creditCount
    )
}

fun createCryptocurrencyResponse(
    id: Long = 1,
    name: String = "Bitcoin",
    symbol: String = "BTC",
    coinMarketRank: Int = 5,
    quote: QuoteResponse = createQuoteResponse()
): CryptocurrencyResponse {
    return CryptocurrencyResponse(
        id,
        name,
        symbol, coinMarketRank, quote
    )
}

fun createQuoteResponse(type: QuoteTypeResponse = createQuoteTypeResponse()): QuoteResponse {
    return QuoteResponse(type)
}

fun createQuoteTypeResponse(
    price: Double = 9283.92,
    volume24h: Double = 715568.0000
): QuoteTypeResponse {
    return QuoteTypeResponse(
        price,
        volume24h,
    )
}