package com.baset.crypto.data.source.local

import androidx.room.*
import com.baset.crypto.data.entity.db.CryptocurrencyDetailEntity
import com.baset.crypto.data.entity.db.CryptocurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDAO {

    /**
     * Transactions
     */
    @Transaction
    suspend fun insertOrUpdateCryptocurrencies(cryptocurrencies: List<CryptocurrencyEntity>) {
        cryptocurrencies.forEach {
            if (alreadyExistsCryptocurrency(it.id))
                updateCryptocurrency(it)
            else
                insertCryptocurrency(it)
        }
    }

    @Transaction
    suspend fun insertOrUpdateCryptocurrencyDetails(cryptocurrencyDetail: CryptocurrencyDetailEntity?) {
        cryptocurrencyDetail?.let {
            if (alreadyExistsCryptocurrencyDetail(cryptocurrencyDetail.id))
                updateCryptocurrencyDetail(cryptocurrencyDetail)
            else
                insertCryptocurrencyDetail(cryptocurrencyDetail)
        }
    }

    /**
     * Query
     */

    @Query("select * from cryptocurrency_detail WHERE id = :id LIMIT 1")
    fun getCryptocurrencyDetail(id: Int): Flow<CryptocurrencyDetailEntity?>

    @Query("select * from cryptocurrencies")
    fun getCryptocurrencies(): Flow<List<CryptocurrencyEntity?>>

    /**
     * Insert
     */

    @Insert
    suspend fun insertCryptocurrency(crypto: CryptocurrencyEntity)

    @Insert
    suspend fun insertCryptocurrencyDetail(cryptoDetail: CryptocurrencyDetailEntity)

    /**
     * Update
     */
    @Update
    suspend fun updateCryptocurrency(crypto: CryptocurrencyEntity)

    @Update
    suspend fun updateCryptocurrencyDetail(cryptoDetail: CryptocurrencyDetailEntity)

    /**
     * Conditional
     */
    @Query("select exists (select * from cryptocurrencies WHERE id = :id LIMIT 1)")
    suspend fun alreadyExistsCryptocurrency(id: Long): Boolean

    @Query("select exists (select * from cryptocurrency_detail WHERE id = :id LIMIT 1)")
    suspend fun alreadyExistsCryptocurrencyDetail(id: Long): Boolean
}