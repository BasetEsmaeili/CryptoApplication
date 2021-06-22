package com.baset.crypto.trader.di.main

import com.baset.crypto.trader.di.app.AppComponent
import com.baset.crypto.trader.di.scope.ScreenScope
import com.baset.crypto.trader.ui.main.MainActivity
import dagger.Component

@Component(modules = [MainBindsModule::class], dependencies = [AppComponent::class])
@ScreenScope
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}