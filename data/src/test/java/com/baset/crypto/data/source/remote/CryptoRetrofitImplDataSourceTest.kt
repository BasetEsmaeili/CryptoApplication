package com.baset.crypto.data.source.remote

import com.baset.crypto.data.entity.CryptocurrenciesResponse
import com.baset.crypto.data.mapper.*
import com.baset.crypto.data.utils.network.ResponseHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class CryptoRetrofitImplDataSourceTest {
    private val apiService = mock<ApiService>()
    private val responseHandler = mock<ResponseHandler>()
    private val mapper: CryptocurrenciesResponseToCryptocurrencyEntityListMapper = mock()
    private val source = CryptoRetrofitImplDataSource(apiService, responseHandler, mapper)

    @Before
    fun setUp() {
        reset(apiService, responseHandler)
    }

    @Test
    fun getCryptocurrenciesCallsResponseHandlerGetErrorResultIfErrorOccurred() = runBlockingTest {
        val exceptedException = IllegalArgumentException()
        whenever(apiService.getCryptocurrencies(1, 20)).thenThrow(exceptedException)
        source.getCryptocurrencies(1, 20)
        verify(responseHandler).getErrorResult<CryptocurrenciesResponse>(exceptedException)
        verifyNoMoreInteractions(responseHandler)
    }
}