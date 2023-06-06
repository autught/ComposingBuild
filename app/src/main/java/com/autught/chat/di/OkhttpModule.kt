package com.autught.chat.di

import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkhttpModule {

    @Provides
    @Singleton
    fun provideOkhttp():OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(10,TimeUnit.MINUTES)
            .writeTimeout(10,TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(8,60,TimeUnit.SECONDS))
            .build()
    }
}