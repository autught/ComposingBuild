package com.autught.chat.main

import android.view.ViewStub

interface ILoading {

    fun loading(stub:ViewStub)

    fun loadedSuccess()

    fun loadedFail()
}