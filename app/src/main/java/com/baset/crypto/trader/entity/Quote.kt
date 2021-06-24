package com.baset.crypto.trader.entity

data class Quote(
    val usd: QuoteType
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            null -> false
            is Quote -> usd == other.usd
            else -> false
        }
    }
}