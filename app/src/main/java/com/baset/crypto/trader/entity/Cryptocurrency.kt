package com.baset.crypto.trader.entity

data class Cryptocurrency(
    val id: Long,
    val name: String,
    val symbol: String,
    val quote: Quote
) {
    override fun equals(other: Any?): Boolean {
        return other is Cryptocurrency && id == other.id
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    fun equalsContent(other: Any?): Boolean {
        return other is Cryptocurrency && other == this && name == other.name && symbol == other.symbol && quote == other.quote
    }
}
