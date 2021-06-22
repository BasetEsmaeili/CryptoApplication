package com.baset.crypto.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baset.crypto.data.entity.db.CryptocurrencyEntity
import com.baset.crypto.data.entity.db.CryptocurrencyDetailEntity

@Database(
    entities = [
        CryptocurrencyDetailEntity::class,
        CryptocurrencyEntity::class
    ], version = 1, exportSchema = false
)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDAO(): CryptoDAO
}