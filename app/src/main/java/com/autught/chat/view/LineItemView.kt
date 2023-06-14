package com.autught.chat.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.autught.chat.R
import splitties.views.dsl.core.add
import splitties.views.dsl.core.matchParent
import splitties.views.dsl.core.wrapContent

/**
 * setEnable()
 */
class LineItemView(context: Context) : FrameLayout(context) {
    private var mEditDrawable: Drawable? = null
    private var mTextColor: Int = Color.BLACK
    private var mTextSize: Float = 14F
    private var mLabelImg: Drawable? = null
    private val tvTile: TextView

    init {
        mEditDrawable = ContextCompat.getDrawable(context, R.mipmap.ic_enter)
        tvTile = TextView(context).apply {
            setTextColor(mTextColor)
            textSize = mTextSize
        }
        add(tvTile, LayoutParams(matchParent, wrapContent, Gravity.CENTER_VERTICAL))
        if (isEnabled) {
            tvTile.setCompoundDrawables(mLabelImg, null, mEditDrawable, null)
        }
    }

    fun setText(str: String) {
        tvTile.text = str
    }

    fun setText(@StringRes strRes: Int) {
        tvTile.setText(strRes)
    }

    fun setEditDrawable(drawable: Drawable) {
        mEditDrawable = drawable
        val startDrawable = tvTile.compoundDrawables.getOrNull(0)
        tvTile.setCompoundDrawables(startDrawable, null, drawable, null)
    }

    fun setLabelImg(drawable: Drawable) {
        mLabelImg = drawable
        val endDrawable = tvTile.compoundDrawables.getOrNull(2)
        tvTile.setCompoundDrawables(drawable, null, endDrawable, null)
    }

    fun setLabelImgPadding(value: Int) {
        tvTile. compoundDrawablePadding = value
    }
}