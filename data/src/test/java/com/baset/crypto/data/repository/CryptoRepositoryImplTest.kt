package com.baset.crypto.data.repository

import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.domain.source.CryptoLocalDataSource
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@ExperimentalCoroutinesApi
class CryptoRepositoryImplTest {

    private val localDataSource: CryptoLocalDataSource = mock()
    private val remoteDataSource: CryptoRemoteDataSource = mock()
    private val repository = CryptoRepositoryImpl(remoteDataSource, localDataSource)

    @Before
    fun setUp() {
        reset(localDataSource, remoteDataSource)
    }

    @Test
    fun getCryptocurrenciesCallsRemoteDataSource() = runBlockingTest {
        repository.getCryptocurrencies(1, 20)
        verify(remoteDataSource).getCryptocurrencies(1, 20)
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun getCryptocurrenciesCallsLocalDataSource() = runBlockingTest {
        repository.getCryptocurrencies(
            CryptocurrencySortType.NAME,
            SortDirection.ASCENDING,
            CryptocurrencyFilterType.ALL,
            TagFilterType.ALL
        )
        verify(localDataSource).getCryptocurrencies(
            CryptocurrencySortType.NAME,
            SortDirection.ASCENDING,
            CryptocurrencyFilterType.ALL,
            TagFilterType.ALL
        )
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun insertCryptocurrenciesCallsLocalDataSource() = runBlockingTest {
        repository.insertCryptocurrencies(arrayListOf())
        verify(localDataSource).insertCryptocurrencies(arrayListOf())
        verifyNoMoreInteractions(localDataSource)
    }
}