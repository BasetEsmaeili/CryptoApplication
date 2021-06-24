package com.baset.crypto.trader.di.filter

import androidx.lifecycle.ViewModel
import com.baset.crypto.trader.di.qualifier.ViewModelKey
import com.baset.crypto.trader.ui.filter.SortAndFilterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SortAndFilterBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(SortAndFilterViewModel::class)
    abstract fun bindSortAndFilterViewModel(sortAndFilterViewModel: SortAndFilterViewModel): ViewModel
}