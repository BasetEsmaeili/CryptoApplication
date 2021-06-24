package com.baset.crypto.trader.entity

data class Quote(
    val usd: QuoteType
) {
    override fun equals(other: Any?): Boolean {
        return other is Quote && usd == other.usd
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}