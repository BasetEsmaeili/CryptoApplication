package com.baset.crypto.trader.di.cryptolist

import androidx.lifecycle.ViewModel
import com.baset.crypto.trader.di.qualifier.ViewModelKey
import com.baset.crypto.trader.ui.cryptolist.CryptoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CryptoListBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    abstract fun bindCryptoListViewModel(cryptoListViewModel: CryptoListViewModel): ViewModel
}