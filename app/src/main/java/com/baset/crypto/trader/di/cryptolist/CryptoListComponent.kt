package com.baset.crypto.trader.di.cryptolist

import com.baset.crypto.trader.di.app.AppComponent
import com.baset.crypto.trader.di.scope.ScreenScope
import com.baset.crypto.trader.ui.cryptolist.CryptoListFragment
import dagger.Component

@ScreenScope
@Component(modules = [CryptoListBindsModule::class], dependencies = [AppComponent::class])
interface CryptoListComponent {
    fun inject(cryptoListFragment: CryptoListFragment)
}