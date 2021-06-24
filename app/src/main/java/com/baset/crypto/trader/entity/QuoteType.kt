package com.baset.crypto.trader.entity

data class QuoteType(
    val price: Double,
    val percentChange24h: Double,
    val marketCap: Double
) {
    override fun equals(other: Any?): Boolean {
        return other is QuoteType && price == other.price && percentChange24h == other.percentChange24h && marketCap == other.marketCap
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}