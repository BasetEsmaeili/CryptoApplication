package com.baset.crypto.data.di.local

import android.content.Context
import com.baset.crypto.data.source.local.CryptoDAO
import com.baset.crypto.data.source.local.CryptoDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalStorageScope

@Component(modules = [LocalStorageModule::class])
@LocalStorageScope
interface LocalStorageComponent {
    fun provideCryptoDatabase(): CryptoDatabase
    fun provideCryptoDAO(): CryptoDAO

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): LocalStorageComponent
    }
}