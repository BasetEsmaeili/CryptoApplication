package com.baset.crypto.trader

import android.app.Application
import com.baset.crypto.data.di.local.DaggerLocalStorageComponent
import com.baset.crypto.data.di.netwrok.DaggerNetworkComponent
import com.baset.crypto.trader.di.app.AppComponent
import com.baset.crypto.trader.di.app.AppComponentProvider
import com.baset.crypto.trader.di.app.DaggerAppComponent

class CryptoApplication : Application(), AppComponentProvider {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        val networkComponent = DaggerNetworkComponent.factory().create(this)
        val localStorageComponent = DaggerLocalStorageComponent.factory().create(this)
        appComponent = DaggerAppComponent.factory().create(localStorageComponent, networkComponent).also {
                it.inject(this)
            }
    }

    override fun provideAppComponent(): AppComponent = appComponent
}