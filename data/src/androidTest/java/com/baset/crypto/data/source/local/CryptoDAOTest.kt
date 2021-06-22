package com.baset.crypto.data.source.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.baset.crypto.data.createDbCryptocurrencyDetailEntity
import com.baset.crypto.data.createDbCryptocurrencyEntity
import com.baset.crypto.data.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class CryptoDAOTest {
    private lateinit var cryptoDAO: CryptoDAO
    private lateinit var db: CryptoDatabase
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java)
            .allowMainThreadQueries().build()
        cryptoDAO = db.cryptoDAO()
    }

    @Test
    fun insertCryptocurrency() = dispatcher.runBlockingTest {
        val cryptocurrencyEntity = createDbCryptocurrencyEntity()
        cryptoDAO.insertCryptocurrency(cryptocurrencyEntity)
        val cryptocurrencies = cryptoDAO.getCryptocurrencies().asLiveData().getOrAwaitValue()
        assertThat(cryptocurrencies).isNotEmpty()
    }

    @Test
    fun insertCryptocurrencyDetail() = dispatcher.runBlockingTest {
        val cryptoDetailEntity = createDbCryptocurrencyDetailEntity()
        cryptoDAO.insertCryptocurrencyDetail(cryptoDetailEntity)
        val cryptoDetail = cryptoDAO.getCryptocurrencyDetail(1).asLiveData().getOrAwaitValue()
        assertThat(cryptoDetail).isNotNull()
    }

    @Test
    fun updateCryptocurrency() = dispatcher.runBlockingTest {
        val oldCrypto = createDbCryptocurrencyEntity()
        val newCrypto =
            createDbCryptocurrencyEntity(name = "Bitco", symbol = "btc", coinMarketRank = 8)
        cryptoDAO.insertCryptocurrency(oldCrypto)
        cryptoDAO.updateCryptocurrency(newCrypto)
        val result = cryptoDAO.getCryptocurrencies().asLiveData().getOrAwaitValue()[0]
        assertThat(result).isEqualTo(newCrypto)
    }

    @Test
    fun updateCryptocurrencyDetail() = dispatcher.runBlockingTest {
        val oldCrypto = createDbCryptocurrencyDetailEntity()
        val newCrypto =
            createDbCryptocurrencyDetailEntity(name = "BITCOIN", symbol = "btc", category = "token")
        cryptoDAO.insertCryptocurrencyDetail(oldCrypto)
        cryptoDAO.updateCryptocurrencyDetail(newCrypto)
        val result = cryptoDAO.getCryptocurrencyDetail(1).asLiveData().getOrAwaitValue()
        assertThat(result).isEqualTo(newCrypto)
    }

    @Test
    fun alreadyExistsCryptocurrency() = dispatcher.runBlockingTest {
        val cryptocurrencyEntity = createDbCryptocurrencyEntity()
        cryptoDAO.insertCryptocurrency(cryptocurrencyEntity)
        val result = cryptoDAO.alreadyExistsCryptocurrency(cryptocurrencyEntity.id)
        assertThat(result).isTrue()
    }

    @Test
    fun alreadyExistsCryptocurrencyDetail() = dispatcher.runBlockingTest {
        val cryptoDetailEntity = createDbCryptocurrencyDetailEntity()
        cryptoDAO.insertCryptocurrencyDetail(cryptoDetailEntity)
        val result = cryptoDAO.alreadyExistsCryptocurrencyDetail(cryptoDetailEntity.id)
        assertThat(result).isTrue()
    }

    @Test
    fun clearCryptocurrencies() = dispatcher.runBlockingTest {
        val cryptocurrencyEntity = createDbCryptocurrencyEntity()
        cryptoDAO.insertCryptocurrency(cryptocurrencyEntity)
        cryptoDAO.clearCryptocurrencies()
        val result = cryptoDAO.getCryptocurrencies().asLiveData().getOrAwaitValue()
        assertThat(result).isEmpty()
    }

    @After
    fun tearDown() {
        db.close()
    }
}