package com.autught.chat.main

import android.view.ViewStub

class LoadingImp:ILoading {
    override fun loading(stub:ViewStub) {
        stub.setOnInflateListener()
    }

    override fun loadedSuccess() {

    }

    override fun loadedFail() {

    }
}