package com.baset.crypto.trader.di.filter

import com.baset.crypto.trader.di.app.AppComponent
import com.baset.crypto.trader.di.scope.ScreenScope
import com.baset.crypto.trader.ui.filter.SortAndFilterBottomSheet
import dagger.Component

@Component(modules = [SortAndFilterBindsModule::class],dependencies = [AppComponent::class])
@ScreenScope
interface SortAndFilterComponent {
    fun inject(sortAndFilterBottomSheet: SortAndFilterBottomSheet)
}