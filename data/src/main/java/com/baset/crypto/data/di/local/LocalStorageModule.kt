package com.baset.crypto.data.di.local

import android.content.Context
import androidx.room.Room
import com.baset.crypto.data.common.DataConstants.TableNames.DATABASE_NAME
import com.baset.crypto.data.source.local.CryptoDatabase
import dagger.Module
import dagger.Provides

@Module
object LocalStorageModule {
    @Provides
    @LocalStorageScope
    @JvmStatic
    fun provideRoomDb(context: Context) =
        Room.databaseBuilder(context, CryptoDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @JvmStatic
    @LocalStorageScope
    fun provideCryptoDAO(database: CryptoDatabase) = database.cryptoDAO()

}