package com.autught.chat.ui

import android.content.Context
import android.view.View
import android.widget.TextView
import splitties.dimensions.dp
import splitties.views.dsl.constraintlayout.constraintLayout
import splitties.views.dsl.core.Ui
import splitties.views.dsl.core.textView

class LoginUI(override val ctx: Context) : Ui {
    override val root: View
        get() = tree


    private val tree = constraintLayout {

    }

    private fun generateButtonByStr(btnStr: String): TextView {
        return textView {
            minHeight = dp(44)
        }
    }
}