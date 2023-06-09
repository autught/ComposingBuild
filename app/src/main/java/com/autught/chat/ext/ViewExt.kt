package com.autught.chat.ext

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.bumptech.glide.Glide

object ViewExt {

    /**
     * 优化文字测量效率（异步）
     * 使用条件：
     *          1.文本总字节>200
     *          2.使用{@link TextViewCompat#getTextMetricsParams(TextView)}之后不要在修改TextView属性
     */
    fun AppCompatTextView.setTextFutureExt(text: String) =
        setTextFuture(
            PrecomputedTextCompat.getTextFuture(
                text,
                TextViewCompat.getTextMetricsParams(this),
                null
            )
        )

    /**
     * 同上
     */
    fun AppCompatEditText.setTextFutureExt(text: String) =
        setText(
            PrecomputedTextCompat.create(text, TextViewCompat.getTextMetricsParams(this))
        )

    fun AppCompatImageView.loadImage(url: String) {
        Glide.with(this).load(url).into(this)
    }

    fun View.corner(radius: Float) {
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(p0: View?, p1: Outline?) {
                p0?.let {
                    p1?.setRoundRect(0, 0, it.width, it.height, radius)
                }
            }
        }
    }

}