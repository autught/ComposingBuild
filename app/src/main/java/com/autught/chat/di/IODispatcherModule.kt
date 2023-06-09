package com.autught.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class IODispatcherModule {
    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineContext {
        return Dispatchers.IO
    }
}