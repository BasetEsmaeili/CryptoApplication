package com.baset.crypto.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.PERCENT_CHANGE_24H
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.QUOTE_COIN_MARKET_RANK
import com.baset.crypto.data.common.DataConstants.TableNames.TABLE_CRYPTO

@Entity(tableName = TABLE_CRYPTO)
data class CryptocurrencyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val symbol: String,
    @ColumnInfo(name = QUOTE_COIN_MARKET_RANK)
    val coinMarketRank: Int,
    val usdPrice:Double,
    @ColumnInfo(name = PERCENT_CHANGE_24H)
    val percentChange24h: Double
)
