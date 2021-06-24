package com.baset.crypto.trader.di.app

import android.content.Context
import com.baset.crypto.data.di.local.LocalStorageComponent
import com.baset.crypto.data.di.netwrok.NetworkComponent
import com.baset.crypto.domain.repository.CryptoRepository
import com.baset.crypto.domain.source.CryptoLocalDataSource
import com.baset.crypto.domain.source.CryptoRemoteDataSource
import com.baset.crypto.trader.CryptoApplication
import com.baset.crypto.trader.utils.threading.DispatcherProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@AppScope
@Component(
    modules = [AppBindsModule::class],
    dependencies = [LocalStorageComponent::class, NetworkComponent::class]
)
interface AppComponent {
    fun inject(app: CryptoApplication)
    fun provideCryptoRepository(): CryptoRepository
    fun provideCryptoLocalDataSource(): CryptoLocalDataSource
    fun provideCryptoRemoteDataSource(): CryptoRemoteDataSource
    fun provideDispatcherProvider(): DispatcherProvider
    fun provideContext(): Context

    @Component.Factory
    interface Factory {
        fun create(
            localStorageComponent: LocalStorageComponent,
            networkComponent: NetworkComponent,
            @BindsInstance context: Context
        ): AppComponent
    }
}