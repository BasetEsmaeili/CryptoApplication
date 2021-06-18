package com.baset.crypto.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * a Contract for create UseCases
 */
abstract class BaseUseCase<out T, in Params> {
    abstract operator fun invoke(params: Params? = null, dispatcher: CoroutineDispatcher): Flow<T>
}