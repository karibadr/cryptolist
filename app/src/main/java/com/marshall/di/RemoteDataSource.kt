package com.marshall.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSource {

    @Provides
    fun provideAPIUrl()= "https://api.wazirx.com/".toHttpUrl()

}