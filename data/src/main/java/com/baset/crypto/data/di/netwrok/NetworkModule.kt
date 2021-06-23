package com.baset.crypto.data.di.netwrok

import com.baset.crypto.data.common.DataConstants.NetworkConfig.BASE_URL
import com.baset.crypto.data.source.remote.ApiService
import com.baset.crypto.data.utils.network.AuthInterceptor
import com.baset.crypto.data.utils.network.NetworkCheckInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ExperimentalSerializationApi
@Module
object NetworkModule {

    @Provides
    @JvmStatic
    @NetworkScope
    fun provideRetrofit(client: Lazy<OkHttpClient>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory {
                client.get().newCall(it)
            }
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @JvmStatic
    @NetworkScope
    fun provideApiService(retrofit: Lazy<Retrofit>): ApiService {
        return retrofit.get().create(ApiService::class.java)
    }

    @Provides
    @JvmStatic
    @NetworkScope
    fun provideOkHttp(
        authInterceptor: AuthInterceptor,
        networkCheckInterceptor: NetworkCheckInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(networkCheckInterceptor)
            .build()
    }
}