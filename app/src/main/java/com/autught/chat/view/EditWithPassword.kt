package com.autught.chat.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import com.autught.chat.R

class EditCanSee @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatEditText(context, attr, defStyle) {
    private var mPaint: Paint? = null
    private var mLineColor: Int? = -1
    private var mSeeDrawable: Drawable? = null
    private var mDisplayFlag: Boolean = false

    init {
        val array =
            getContext().obtainStyledAttributes(attr, R.styleable.EditCanSee, defStyle, 0)
        mLineColor = array.getColor(R.styleable.EditCanSee_ecs_lineColor, -1)
        mSeeDrawable = array.getDrawable(R.styleable.EditCanSee_ecs_seeDrawable)
        array.recycle()
        if (mSeeDrawable != null) {
            setCompoundDrawables(null, null, mSeeDrawable, null)
        }
        initPaint()
        if (mLineColor != -1) {
            mPaint!!.color = mLineColor!!
        }
    }

    private fun initPaint() {
        if (mPaint == null) {
            mPaint = Paint()
            mPaint!!.isAntiAlias = true
            mPaint!!.strokeCap = Paint.Cap.ROUND
            mPaint!!.strokeWidth = 2F
        }
    }

    fun setLineColor(color: Int) {
        mLineColor = color
        mPaint!!.color = color
        invalidate()
    }

    fun setSeeDrawable(drawable:Drawable){
        mSeeDrawable=drawable
        setCompoundDrawables(null, null, mSeeDrawable, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint?.let {
            canvas?.drawLine(0F, height - 2 / 2F, width.toFloat(), height - 2 / 2F, it)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP &&
            mSeeDrawable != null &&
            event.x > width - paddingRight - mSeeDrawable!!.intrinsicWidth
        ) {
            if (!mDisplayFlag) {
                // display password text, for example "123456"
                transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // hide password, display "."
                transformationMethod = PasswordTransformationMethod.getInstance()
            }
            mDisplayFlag = !mDisplayFlag;
            postInvalidate()
            return true
        }
        return super.onTouchEvent(event)
    }

}