package com.autught.chat.di

import androidx.room.Room
import com.autught.chat.local.AppDatabase
import com.autught.chat.main.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import splitties.init.appCtx
import splitties.init.directBootCtx
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    /**
     * 单进程唯一实例
     */
    @Provides
    @Singleton
    fun provideDb(): AppDatabase {
        return Room.databaseBuilder(directBootCtx, AppDatabase::class.java, AppConfig.DB).build()
    }
}