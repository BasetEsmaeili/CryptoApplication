package com.baset.crypto.trader

import android.app.Application
import com.baset.crypto.data.di.context.DaggerContextComponent
import com.baset.crypto.data.di.local.DaggerLocalStorageComponent
import com.baset.crypto.data.di.netwrok.DaggerNetworkComponent
import com.baset.crypto.trader.di.AppComponent
import com.baset.crypto.trader.di.DaggerAppComponent

class CryptoApplication : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        val contextComponent = DaggerContextComponent.factory().create(this)
        val networkComponent = DaggerNetworkComponent.factory().create(contextComponent)
        val localStorageComponent = DaggerLocalStorageComponent.factory().create(contextComponent)
        appComponent = DaggerAppComponent.factory().create(
            contextComponent, localStorageComponent, networkComponent
        ).also {
            it.inject(this)
        }
    }
}