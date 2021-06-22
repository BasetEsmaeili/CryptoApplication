package com.baset.crypto.trader.utils.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}