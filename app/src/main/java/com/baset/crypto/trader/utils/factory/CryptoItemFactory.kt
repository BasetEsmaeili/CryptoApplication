package com.baset.crypto.trader.utils.factory

import android.content.Context
import android.text.SpannedString
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.baset.crypto.trader.R
import com.baset.crypto.trader.utils.getColorCompat
import com.baset.crypto.trader.utils.isNegative
import com.baset.crypto.trader.utils.round
import javax.inject.Inject

class CryptoItemFactory @Inject constructor(private val context: Context) {
    fun formatPrice(price: Double): String {
        return "${price.round(4)}$"
    }

    fun formatPercentChange(percentChange: Double): SpannedString {
        val roundedPercent = percentChange.round(2)
        return buildSpannedString {
            color(context.getColorCompat(R.color.black)) { append(context.getString(R.string.label_twenty_four_hour)) }
            color(
                if (roundedPercent.isNegative()) context.getColorCompat(R.color.negative_percent)
                else context.getColorCompat(R.color.positive_percent)
            ) {
                append(" ")
                append("$roundedPercent%") }
        }
    }

    fun formatMarketCap(marketCap: Double): String {
        return "${context.getString(R.string.label_market_cap_with_colon)} ${marketCap.round(4)}"
    }
}