package com.baset.crypto.trader.di

import com.baset.crypto.data.di.context.ContextComponent
import com.baset.crypto.data.di.local.LocalStorageComponent
import com.baset.crypto.data.di.netwrok.NetworkComponent
import com.baset.crypto.trader.CryptoApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppBindsModule::class])
interface AppComponent {
    fun inject(app: CryptoApplication)
    fun provideContextComponent(): ContextComponent
    fun provideLocalStorageComponent(): LocalStorageComponent
    fun provideNetworkComponent(): NetworkComponent

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance contextComponent: ContextComponent,
            @BindsInstance localStorageComponent: LocalStorageComponent,
            @BindsInstance networkComponent: NetworkComponent
        ): AppComponent
    }
}