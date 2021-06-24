package com.baset.crypto.trader.utils.threading

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun default(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}
