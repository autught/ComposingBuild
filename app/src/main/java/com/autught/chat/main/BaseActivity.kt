package com.autught.chat.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), IScreenAdapt {

    override fun onCreate(savedInstanceState: Bundle?) {
        adapt(this, application, adaptOrientation(), adaptDesign())
        super.onCreate(savedInstanceState)
    }

    override fun adaptOrientation(): Int {
        return Configuration.ORIENTATION_PORTRAIT
    }

    override fun adaptDesign(): Int {
        return 360
    }
}