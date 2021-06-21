package com.baset.crypto.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * a Contract for create UseCases
 */
abstract class BaseFlowUseCase<out Result, in Params> {
    protected abstract fun createFlow(
        params: Params?
    ): Flow<Result>

    operator fun invoke(params: Params? = null, dispatcher: CoroutineDispatcher) =
        createFlow(params).flowOn(dispatcher)
}