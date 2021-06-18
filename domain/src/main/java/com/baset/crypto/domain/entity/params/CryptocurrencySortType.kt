package com.baset.crypto.domain.entity.params

enum class CryptocurrencySortType(val rawValue: String) {
    RANK("market_cap"),
    NAME("name"),
    PRICE("price")
}