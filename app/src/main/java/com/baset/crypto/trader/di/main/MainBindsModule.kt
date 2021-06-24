package com.baset.crypto.trader.di.main

import androidx.lifecycle.ViewModel
import com.baset.crypto.trader.di.qualifier.ViewModelKey
import com.baset.crypto.trader.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainBindsModule  {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}