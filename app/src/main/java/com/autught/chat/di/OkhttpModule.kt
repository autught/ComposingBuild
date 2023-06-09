package com.autught.chat.di

import com.autught.chat.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * 通用接口网络请求注解
 */
const val api="api"

@Module
@InstallIn(SingletonComponent::class)
class OkhttpModule {

    @Provides
    @Singleton
    @Named(api)
    fun provideOkhttp(): OkHttpClient {
        val log = HttpLoggingInterceptor()
        log.setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE)
        return OkHttpClient.Builder()
            .addInterceptor(log)
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(8, 60, TimeUnit.SECONDS))
            .build()
    }
}