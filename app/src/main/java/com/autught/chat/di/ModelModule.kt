package com.autught.chat.di

import com.autught.chat.net.ApiModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModelModule {

    @Provides
    fun provideModel():ApiModel{
        return ApiModel()
    }
}