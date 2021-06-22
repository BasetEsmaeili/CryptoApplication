package com.baset.crypto.data.common

object DataConstants {

    object NetworkConfig {
        const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/"
    }

    object Authentication {
        const val KEY_API_KEY = "X-CMC_PRO_API_KEY"
    }

    object RemoteSerialNames {
        const val DATE_ADDED = "date_added"
        const val TWITTER_USER_NAME = "twitter_username"
        const val USD = "USD"
        const val PERCENT_CHANGE_24H = "percent_change_24h"
        const val ERROR_CODE = "error_code"
        const val ERROR_MESSAGE = "error_message"
        const val CREDIT_COUNT = "credit_count"
        const val QUOTE_COIN_MARKET_RANK = "market_cap"
    }

    object TableNames {
        const val DATABASE_NAME = "crypto.db"
        const val TABLE_CRYPTO_DETAILS = "cryptocurrency_detail"
        const val TABLE_CRYPTO = "cryptocurrencies"
    }
}