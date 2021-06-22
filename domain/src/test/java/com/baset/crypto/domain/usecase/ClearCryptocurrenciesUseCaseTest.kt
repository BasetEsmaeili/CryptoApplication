package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.repository.CryptoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@ExperimentalCoroutinesApi
class ClearCryptocurrenciesUseCaseTest {
    private val repo: CryptoRepository = mock()
    private val useCase = ClearCryptocurrenciesUseCase(repo)

    @Before
    fun setUp() {
        reset(repo)
    }

    @Test
    fun invokeClearCryptocurrenciesUseCaseTestCallsRepository() = runBlockingTest {
        useCase()
        verify(repo).clearCryptocurrencies()
        verifyNoMoreInteractions(repo)
    }
}