package com.baset.crypto.data.repository

import com.baset.crypto.data.createCryptocurrencyDetailEntity
import com.baset.crypto.data.createCryptocurrencyEntity
import com.baset.crypto.data.createRemoteCryptocurrenciesParams
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.source.CryptoLocalDataSource
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

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
        val params = createRemoteCryptocurrenciesParams()
        whenever(
            remoteDataSource.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(Result.success(emptyList()))
        repository.getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        verify(remoteDataSource).getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun getCryptocurrenciesCallsLocalDataSourceIfResultStatusIsSuccess() = runBlockingTest {
        val exceptedData = listOf(createCryptocurrencyEntity())
        val params = createRemoteCryptocurrenciesParams()
        whenever(
            remoteDataSource.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(Result.success(exceptedData))
        repository.getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        verify(localDataSource).insertCryptocurrencies(exceptedData)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getCryptocurrenciesCallsLocalDataSource() = runBlockingTest {
        repository.getCryptocurrencies()
        verify(localDataSource).getCryptocurrencies()
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getRemoteCryptocurrencyDetailCallsRemoteDataSource() = runBlockingTest {
        val exceptedResult = createCryptocurrencyDetailEntity()
        whenever(remoteDataSource.getCryptocurrencyDetail(1)).thenReturn(
            Result.success(exceptedResult)
        )
        repository.getRemoteCryptocurrencyDetail(1)
        verify(remoteDataSource).getCryptocurrencyDetail(1)
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun getRemoteCryptocurrencyDetailCallsLocalDataSourceIfResultStatusIsSuccess() =
        runBlockingTest {
            val exceptedResult = createCryptocurrencyDetailEntity()
            whenever(remoteDataSource.getCryptocurrencyDetail(1)).thenReturn(
                Result.success(exceptedResult)
            )
            repository.getRemoteCryptocurrencyDetail(1)
            verify(localDataSource).insertCryptocurrencyDetail(exceptedResult)
            verifyNoMoreInteractions(localDataSource)
        }

    @Test
    fun getLocalCryptocurrencyDetailCallsLocalDataSource() = runBlockingTest {
        repository.getLocalCryptocurrencyDetail(1)
        verify(localDataSource).getCryptocurrencyDetail(1)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun clearCryptocurrenciesCallsLocalDataSource() = runBlockingTest {
        repository.clearCryptocurrencies()
        verify(localDataSource).clearCryptocurrencies()
        verifyNoMoreInteractions(localDataSource)
    }
}