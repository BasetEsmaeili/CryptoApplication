package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.createCryptocurrencyEntity
import com.baset.crypto.domain.entity.CryptocurrencyEntity
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
class GetLocalCryptocurrenciesUseCaseTest {
    private val repo: CryptoRepository = mock()
    private val useCase: GetLocalCryptocurrenciesUseCase = GetLocalCryptocurrenciesUseCase(repo)
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        reset(repo)
    }

    @Test
    fun invokeGetLocalCryptocurrenciesUseCaseCallsRepository() = runBlockingTest {
        useCase(dispatcher = dispatcher)
        verify(repo).getCryptocurrencies()
        verifyNoMoreInteractions(repo)
    }

    @Test
    fun invokeGetLocalCryptocurrenciesUseCaseReturnsDataIfDataAvailable() = runBlockingTest {
        val fakeFlow = flow { emit(listOf(createCryptocurrencyEntity())) }
        whenever(
            repo.getCryptocurrencies()
        ).thenReturn(fakeFlow)
        val result = useCase(dispatcher = dispatcher).single()
        Truth.assertThat(result).isNotEmpty()
    }

    @Test
    fun invokeGetLocalCryptocurrenciesUseCaseReturnsEmptyListIfDataNotAvailable() =
        runBlockingTest {
            val fakeFlow = flow<List<CryptocurrencyEntity?>?> { emit(emptyList()) }
            whenever(
                repo.getCryptocurrencies()
            ).thenReturn(fakeFlow)
            val result = useCase(dispatcher = dispatcher).single()
            Truth.assertThat(result).isEmpty()
        }

}