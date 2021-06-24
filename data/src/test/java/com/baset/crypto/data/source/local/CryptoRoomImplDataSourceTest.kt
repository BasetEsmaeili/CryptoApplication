package com.baset.crypto.data.source.local

import com.baset.crypto.data.createCryptocurrencyDetailEntity
import com.baset.crypto.data.createCryptocurrencyEntity
import com.baset.crypto.data.mapper.DbCryptocurrencyDetailEntityToCryptocurrencyDeatilEntityMapper
import com.baset.crypto.data.mapper.DbCryptocurrencyEntityListToCryptocurrencyEntityListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@ExperimentalCoroutinesApi
class CryptoRoomImplDataSourceTest {
    private val cryptoDAO: CryptoDAO = mock()
    private val cryptocurrencyMapper: DbCryptocurrencyEntityListToCryptocurrencyEntityListMapper =
        DbCryptocurrencyEntityListToCryptocurrencyEntityListMapper()
    private val cryptoDetailMapper: DbCryptocurrencyDetailEntityToCryptocurrencyDeatilEntityMapper =
        DbCryptocurrencyDetailEntityToCryptocurrencyDeatilEntityMapper()
    private val source: CryptoRoomImplDataSource =
        CryptoRoomImplDataSource(cryptoDAO, cryptocurrencyMapper, cryptoDetailMapper)

    @Before
    fun setUp() {
        reset(cryptoDAO)
    }

    @Test
    fun insertCryptocurrenciesCallsCryptoDAO() = runBlockingTest {
        val cryptocurrencies = listOf(createCryptocurrencyEntity())
        source.insertCryptocurrencies(cryptocurrencies)
        verify(cryptoDAO).insertOrUpdateCryptocurrencies(
            cryptocurrencyMapper.mapToEntity(
                cryptocurrencies
            )
        )
        verifyNoMoreInteractions(cryptoDAO)
    }

    @Test
    fun getCryptocurrenciesCallsCryptoDAO() = runBlockingTest {
        source.getCryptocurrencies()
        verify(cryptoDAO).getCryptocurrencies()
        verifyNoMoreInteractions(cryptoDAO)
    }

    @Test
    fun insertCryptocurrencyDetailCallCryptoDAO() = runBlockingTest {
        val cryptoDetail = createCryptocurrencyDetailEntity()
        source.insertCryptocurrencyDetail(cryptoDetail)
        verify(cryptoDAO).insertOrUpdateCryptocurrencyDetails(
            cryptoDetailMapper.mapToEntity(
                cryptoDetail
            )
        )
        verifyNoMoreInteractions(cryptoDAO)
    }

    @Test
    fun getCryptocurrencyDetailCallCryptoDAO() = runBlockingTest {
        source.getCryptocurrencyDetail(1)
        verify(cryptoDAO).getCryptocurrencyDetail(1)
        verifyNoMoreInteractions(cryptoDAO)
    }

    @Test
    fun clearCryptocurrenciesCallsCryptoDAO() = runBlockingTest {
        source.clearCryptocurrencies()
        verify(cryptoDAO).clearCryptocurrencies()
        verifyNoMoreInteractions(cryptoDAO)
    }
}