package com.baset.crypto.domain.base

/**
 * a Contract for create UseCases
 */
abstract class BaseUseCase<out Result, in Params> {
    protected abstract suspend fun create(
        params: Params? = null
    ): Result

    suspend operator fun invoke(params: Params?) = create(params)
}