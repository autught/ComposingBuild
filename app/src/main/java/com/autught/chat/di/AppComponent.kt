package com.autught.chat.di

import com.autught.chat.main.App
import dagger.Component

@Component
interface AppComponent {
    fun inject(application: App)
}