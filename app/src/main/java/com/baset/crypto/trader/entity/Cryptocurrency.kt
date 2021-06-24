package com.baset.crypto.trader.entity

data class Cryptocurrency(
    val id: Long,
    val name: String,
    val symbol: String,
    val quote: Quote
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            null -> false
            is Cryptocurrency -> id == other.id && name == other.name && symbol == other.symbol && quote == other.quote
            else -> false
        }
    }
}
