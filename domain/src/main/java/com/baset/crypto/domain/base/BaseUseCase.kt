package com.baset.crypto.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * a Contract for create UseCases
 */
abstract class BaseUseCase<out T, in Params> {
    protected abstract fun createFlow(
        params: Params? = null,
        dispatcher: CoroutineDispatcher
    ): Flow<T>

    operator fun invoke(params: Params?, dispatcher: CoroutineDispatcher) =
        createFlow(params, dispatcher)
}