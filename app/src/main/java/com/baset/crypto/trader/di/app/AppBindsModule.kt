package com.baset.crypto.trader.di.app

import androidx.lifecycle.ViewModelProvider
import com.baset.crypto.data.repository.CryptoRepositoryImpl
import com.baset.crypto.data.source.local.CryptoRoomDataSourceImpl
import com.baset.crypto.data.source.remote.CryptoRetrofitDataSourceImpl
import com.baset.crypto.domain.repository.CryptoRepository
import com.baset.crypto.domain.source.CryptoLocalDataSource
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import com.baset.crypto.trader.utils.factory.ViewModelFactory
import com.baset.crypto.trader.utils.threading.AppDispatcherProvider
import com.baset.crypto.trader.utils.threading.DispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindsModule {
    @Binds
    abstract fun bindCryptoRepository(repositoryImpl: CryptoRepositoryImpl): CryptoRepository

    @Binds
    abstract fun bindCryptoLocalDataSource(source: CryptoRoomDataSourceImpl): CryptoLocalDataSource

    @Binds
    abstract fun bindCryptoRemoteDataSource(source: CryptoRetrofitDataSourceImpl): CryptoRemoteDataSource

    @Binds
    abstract fun bindDispatcherProvider(appDispatcherProvider: AppDispatcherProvider): DispatcherProvider

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}