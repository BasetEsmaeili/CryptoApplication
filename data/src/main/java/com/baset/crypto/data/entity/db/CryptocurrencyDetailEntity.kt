package com.baset.crypto.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.DATE_ADDED
import com.baset.crypto.data.common.DataConstants.RemoteSerialNames.TWITTER_USER_NAME
import com.baset.crypto.data.common.DataConstants.TableNames.TABLE_CRYPTO_DETAILS

@Entity(tableName = TABLE_CRYPTO_DETAILS)
data class CryptocurrencyDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val symbol: String,
    val category: String,
    val description: String,
    val slug: String,
    val logo: String,
    val subreddit: String,
    val notice: String,
    @ColumnInfo(name = DATE_ADDED)
    val dateAdded: String,
    @ColumnInfo(name = TWITTER_USER_NAME)
    val twitterUsername: String,
)