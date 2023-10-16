package com.marshall.di

import android.app.Application
import androidx.room.Room
import com.marshall.data.local.db.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSource {

    @Provides
    fun provideCryptoDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            CryptoDatabase::class.java,
            "crypto_database"
        ).build()
}