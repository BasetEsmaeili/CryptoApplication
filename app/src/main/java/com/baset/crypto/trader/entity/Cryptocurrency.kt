package com.baset.crypto.trader.entity

data class Cryptocurrency(
    val id: Long,
    val name: String,
    val symbol: String,
    val quote: Quote
)
