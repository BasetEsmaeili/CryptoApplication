package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.createCryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.ErrorEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.Status
import com.baset.crypto.domain.entity.params.CryptocurrencyDetailParams
import com.baset.crypto.domain.repository.CryptoRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class GetRemoteCryptocurrencyDetailUseCaseTest {

    private val repo:CryptoRepository = mock()
    private val useCase:GetRemoteCryptocurrencyDetailUseCase = GetRemoteCryptocurrencyDetailUseCase(repo)

    @Before
    fun setUp() {
        reset(repo)
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseCallsRepository() = runBlockingTest {
        useCase(CryptocurrencyDetailParams(1))
        verify(repo).getRemoteCryptocurrencyDetail(1)
        verifyNoMoreInteractions(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invokeGetRemoteCryptocurrencyDetailUseCaseThrowsExceptionIfParamsIsNull() = runBlockingTest {
        useCase(null)
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseReturnsSuccessStatusIfSuccess() = runBlockingTest {
        val successResponse = Result.success(createCryptocurrencyDetailEntity())
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(successResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseReturnsErrorStatusIfErrorOccurred() = runBlockingTest {
        val errorResponse = Result.error<CryptocurrencyDetailEntity>( ErrorEntity.ApiError.InternalServerError)
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(errorResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseReturnsDataIfSuccess() = runBlockingTest {
        val exceptedResponse = createCryptocurrencyDetailEntity()
        val successResponse = Result.success(exceptedResponse)
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(successResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.data).isEqualTo(exceptedResponse)
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseDataIsNullIfErrorOccurred() = runBlockingTest {
        val errorResponse = Result.error<CryptocurrencyDetailEntity>(ErrorEntity.ApiError.InternalServerError)
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(errorResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.data).isNull()
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseErrorTypeMustBeNullIfSuccess() = runBlockingTest {
        val exceptedResponse = createCryptocurrencyDetailEntity()
        val successResponse = Result.success(exceptedResponse)
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(successResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.errorType).isNull()
    }

    @Test
    fun invokeGetRemoteCryptocurrencyDetailUseCaseErrorTypeMustNotBeNullIfErrorOccurred() = runBlockingTest {
        val errorResponse = Result.error<CryptocurrencyDetailEntity>( ErrorEntity.ApiError.InternalServerError)
        whenever(repo.getRemoteCryptocurrencyDetail(1)).thenReturn(errorResponse)
        val result = useCase(CryptocurrencyDetailParams(1))
        Truth.assertThat(result.errorType).isNotNull()
    }
}