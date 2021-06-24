package com.baset.crypto.domain.entity

data class CryptocurrencyEntity(
    val id: Long,
    val name: String,
    val symbol: String,
    val quote: QuoteEntity
)
