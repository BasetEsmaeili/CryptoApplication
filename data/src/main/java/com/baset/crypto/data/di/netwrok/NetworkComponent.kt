package com.baset.crypto.data.di.netwrok

import com.baset.crypto.data.di.context.ContextComponent
import com.baset.crypto.data.utils.network.AuthInterceptor
import com.baset.crypto.data.utils.network.NetworkCheckInterceptor
import com.baset.crypto.data.utils.network.NetworkManager
import com.baset.crypto.data.utils.network.ResponseHandler
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope

@NetworkScope
@Component(modules = [NetworkModule::class], dependencies = [ContextComponent::class])
interface NetworkComponent {
    fun provideRetrofit(): Retrofit
    fun provideOkHttp(): OkHttpClient
    fun provideAuthInterceptor(): AuthInterceptor
    fun provideNetworkInterceptor(): NetworkCheckInterceptor
    fun provideNetworkManager(): NetworkManager
    fun provideResponseHandler(): ResponseHandler

    @Component.Factory
    interface Factory {
        fun create(contextComponent: ContextComponent): NetworkComponent
    }
}