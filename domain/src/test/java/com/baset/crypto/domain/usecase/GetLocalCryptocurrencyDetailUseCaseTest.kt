package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.createCryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.params.CryptocurrencyDetailParams
import com.baset.crypto.domain.repository.CryptoRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class GetLocalCryptocurrencyDetailUseCaseTest {
    private val repo: CryptoRepository = mock()
    private val useCase:GetLocalCryptocurrencyDetailUseCase = GetLocalCryptocurrencyDetailUseCase(repo)
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        reset(repo)
    }

    @Test
    fun invokeGetLocalCryptocurrencyDetailUseCaseTestCallsRepository() = runBlockingTest {
        val params = CryptocurrencyDetailParams(1)
        useCase(params, dispatcher)
        verify(repo).getLocalCryptocurrencyDetail(params.id)
        verifyNoMoreInteractions(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invokeGetLocalCryptocurrencyDetailUseCaseThrowsExceptionIfParamsIsNull() = runBlockingTest {
        useCase(null, dispatcher)
    }

    @Test
    fun invokeGetLocalCryptocurrencyDetailUseCaseReturnsDataIfDataAvailable() = runBlockingTest {
        val params = CryptocurrencyDetailParams(1)
        val fakeFlow = flow { emit(createCryptocurrencyDetailEntity()) }
        whenever(repo.getLocalCryptocurrencyDetail(params.id)).thenReturn(fakeFlow)
        val result = useCase(params, dispatcher).single()
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun invokeGetLocalCryptocurrencyDetailUseCaseReturnsNullIfDataNotAvailable() = runBlockingTest {
        val params = CryptocurrencyDetailParams(1)
        val fakeFlow = flow<CryptocurrencyDetailEntity?> { emit(null) }
        whenever(repo.getLocalCryptocurrencyDetail(params.id)).thenReturn(fakeFlow)
        val result = useCase(params, dispatcher).single()
        Truth.assertThat(result).isNull()
    }

}