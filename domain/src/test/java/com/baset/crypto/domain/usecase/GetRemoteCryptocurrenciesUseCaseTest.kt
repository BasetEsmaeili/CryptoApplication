package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.createCryptocurrencyEntity
import com.baset.crypto.domain.entity.Resource
import com.baset.crypto.domain.entity.Status
import com.baset.crypto.domain.entity.params.GetRemoteCryptocurrenciesParams
import com.baset.crypto.domain.repository.CryptoRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class GetRemoteCryptocurrenciesUseCaseTest {
    private val repo: CryptoRepository = mock()
    private val useCase = GetRemoteCryptocurrenciesUseCase(repo)


    @Before
    fun setup() {
        reset(repo)
    }


    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseCallsRepository() = runBlockingTest {
        useCase(GetRemoteCryptocurrenciesParams(1, 20))
        verify(repo).getCryptocurrencies(1, 20)
        verifyNoMoreInteractions(repo)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsSuccessStatusIfSuccess() = runBlockingTest {
        val successResponse = Resource.success("Operation Complete", listOf(createCryptocurrencyEntity()), 200)
        whenever(repo.getCryptocurrencies(1, 20)).thenReturn(successResponse)
        val result = useCase(GetRemoteCryptocurrenciesParams())
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsErrorStatusIfErrorOccurred() = runBlockingTest {
        val errorResponse = Resource.error("Operation Error", null, 401)
        whenever(repo.getCryptocurrencies(1, 20)).thenReturn(errorResponse)
        val result = useCase(GetRemoteCryptocurrenciesParams())
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsDataIfSuccess() = runBlockingTest {
        val exceptedResponse = listOf(createCryptocurrencyEntity())
        val successResponse = Resource.success("Operation Complete", exceptedResponse, 200)
        whenever(repo.getCryptocurrencies(1, 20)).thenReturn(successResponse)
        val result = useCase(GetRemoteCryptocurrenciesParams())
        assertThat(result.data).isEqualTo(exceptedResponse)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseDataIsNullIfErrorOccurred() = runBlockingTest {
        val errorResponse = Resource.error("Operation Complete", null, 200)
        whenever(repo.getCryptocurrencies(1, 20)).thenReturn(errorResponse)
        val result = useCase(GetRemoteCryptocurrenciesParams())
        assertThat(result.data).isNull()
    }
}