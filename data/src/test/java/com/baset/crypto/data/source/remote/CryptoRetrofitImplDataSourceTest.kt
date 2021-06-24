package com.baset.crypto.data.source.remote

import com.baset.crypto.data.createCryptocurrenciesResponse
import com.baset.crypto.data.createCryptocurrencyDetailsResponse
import com.baset.crypto.data.createRemoteCryptocurrenciesParams
import com.baset.crypto.data.entity.CryptocurrenciesResponse
import com.baset.crypto.data.entity.CryptocurrencyDetailsResponse
import com.baset.crypto.data.mapper.*
import com.baset.crypto.data.utils.network.ResponseHandler
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class CryptoRetrofitImplDataSourceTest {
    private val apiService = mock<ApiService>()
    private val responseHandler = mock<ResponseHandler>()
    private val cryptocurrenciesMapper: CryptocurrenciesResponseToCryptocurrencyEntityListMapper =
        CryptocurrenciesResponseToCryptocurrencyEntityListMapper(
            CryptocurrencyResponseToCryptocurrencyEntityMapper(
                QuoteResponseToQuoteEntityMapper(
                    QuoteTypeResponseToQuoteTypeEntityMapper()
                )
            )
        )
    private val cryptoDetailMapper: CryptocurrencyDetailsResponseToCryptocurrencyDetailEntityMapper =
        CryptocurrencyDetailsResponseToCryptocurrencyDetailEntityMapper()
    private val source = CryptoRetrofitImplDataSource(
        apiService,
        responseHandler,
        cryptocurrenciesMapper,
        cryptoDetailMapper
    )

    @Before
    fun setUp() {
        reset(apiService, responseHandler)
    }

    @Test
    fun getCryptocurrenciesReturnsDataIfSuccess() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val fakeData = createCryptocurrenciesResponse()
        whenever(
            apiService.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy.rawValue,
                params.sortDirection.rawValue,
                params.cryptocurrencyType.rawValue,
                params.tagType.rawValue
            )
        ).thenReturn(fakeData)
        val result = source.getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun getCryptocurrenciesCallsResponseHandlerGetErrorResultIfErrorOccurred() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val exceptedException = IllegalArgumentException()
        whenever(
            apiService.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy.rawValue,
                params.sortDirection.rawValue,
                params.cryptocurrencyType.rawValue,
                params.tagType.rawValue
            )
        ).thenThrow(exceptedException)
        source.getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        verify(responseHandler).getErrorResult<CryptocurrenciesResponse>(exceptedException)
        verifyNoMoreInteractions(responseHandler)
    }

    @Test
    fun getCryptocurrencyDetailReturnsDataIfSuccess() = runBlockingTest {
        val fakeData = createCryptocurrencyDetailsResponse()
        whenever(apiService.getCryptocurrencyDetail(1)).thenReturn(fakeData)
        val result = source.getCryptocurrencyDetail(1)
        assertThat(result.data).isNotNull()
    }

    @Test
    fun getCryptocurrencyDetailCallsResponseHandlerGetErrorResultIfErrorOccurred() =
        runBlockingTest {
            val exceptedException = IllegalArgumentException()
            whenever(apiService.getCryptocurrencyDetail(1)).thenThrow(exceptedException)
            source.getCryptocurrencyDetail(1)
            verify(responseHandler).getErrorResult<CryptocurrencyDetailsResponse>(exceptedException)
            verifyNoMoreInteractions(responseHandler)
        }
}