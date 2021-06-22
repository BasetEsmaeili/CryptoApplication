package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.createCryptocurrencyEntity
import com.baset.crypto.domain.createRemoteCryptocurrenciesParams
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.ErrorEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.Status
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
        val params = createRemoteCryptocurrenciesParams()
        useCase(params)
        verify(repo).getCryptocurrencies(
            params.page,
            params.pageLimit,
            params.sortBy,
            params.sortDirection,
            params.cryptocurrencyType,
            params.tagType
        )
        verifyNoMoreInteractions(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invokeGetRemoteCryptocurrenciesUseCaseThrowsExceptionIfParamsIsNull() = runBlockingTest {
        useCase(null)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsSuccessStatusIfSuccess() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val successResponse = Result.success(listOf(createCryptocurrencyEntity()))
        whenever(
            repo.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(successResponse)
        val result = useCase(params)
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsErrorStatusIfErrorOccurred() =
        runBlockingTest {
            val params = createRemoteCryptocurrenciesParams()
            val errorResponse = Result.error<List<CryptocurrencyEntity>>(ErrorEntity.ApiError.InternalServerError)
            whenever(
                repo.getCryptocurrencies(
                    params.page,
                    params.pageLimit,
                    params.sortBy,
                    params.sortDirection,
                    params.cryptocurrencyType,
                    params.tagType
                )
            ).thenReturn(errorResponse)
            val result = useCase(params)
            assertThat(result.status).isEqualTo(Status.ERROR)
        }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseReturnsDataIfSuccess() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val exceptedResponse = listOf(createCryptocurrencyEntity())
        val successResponse = Result.success(exceptedResponse)
        whenever(
            repo.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(successResponse)
        val result = useCase(params)
        assertThat(result.data).isEqualTo(exceptedResponse)
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseDataIsNullIfErrorOccurred() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val errorResponse = Result.error<List<CryptocurrencyEntity>>(ErrorEntity.ApiError.InternalServerError)
        whenever(
            repo.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(errorResponse)
        val result = useCase(params)
        assertThat(result.data).isNull()
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseErrorTypeMustBeNullIfSuccess() = runBlockingTest {
        val params = createRemoteCryptocurrenciesParams()
        val exceptedResponse = listOf(createCryptocurrencyEntity())
        val successResponse = Result.success(exceptedResponse)
        whenever(
            repo.getCryptocurrencies(
                params.page,
                params.pageLimit,
                params.sortBy,
                params.sortDirection,
                params.cryptocurrencyType,
                params.tagType
            )
        ).thenReturn(successResponse)
        val result = useCase(params)
        assertThat(result.errorType).isNull()
    }

    @Test
    fun invokeGetRemoteCryptocurrenciesUseCaseErrorTypeMustNotBeNullIfErrorOccurred() =
        runBlockingTest {
            val params = createRemoteCryptocurrenciesParams()
            val errorResponse = Result.error<List<CryptocurrencyEntity>>(ErrorEntity.ApiError.InternalServerError)
            whenever(
                repo.getCryptocurrencies(
                    params.page,
                    params.pageLimit,
                    params.sortBy,
                    params.sortDirection,
                    params.cryptocurrencyType,
                    params.tagType
                )
            ).thenReturn(errorResponse)
            val result = useCase(params)
            assertThat(result.errorType).isNotNull()
        }
}