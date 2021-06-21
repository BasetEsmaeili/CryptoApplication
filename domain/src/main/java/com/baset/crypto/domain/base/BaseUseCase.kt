package com.baset.crypto.domain.base

/**
 * a Contract for create UseCases
 */
abstract class BaseUseCase<out Result, in Params> {
    protected abstract suspend fun create(
        params: Params?
    ): Result

    suspend operator fun invoke(params: Params? = null) = create(params)
}