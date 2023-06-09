package com.autught.chat.di

import com.autught.chat.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    /**
     * 提供基于{@link com.autught.chat.BuildConfig#BASE_URL}生成的接口
     */
    @Singleton
    @Provides
    fun provideApiService(@Named(api) retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    //其他service同上
}