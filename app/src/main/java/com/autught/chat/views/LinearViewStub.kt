package com.autught.chat.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import java.lang.ref.WeakReference

/**
 * copy
 */
class LinearViewStub(context: Context) : View(context) {


    private var mInflatedViewRef: WeakReference<FrameLayout>? = null


    init {
        visibility = GONE
        setWillNotDraw(true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    @SuppressLint("MissingSuperCall")
    override fun draw(canvas: Canvas?) {
    }

    override fun dispatchDraw(canvas: Canvas?) {}

    /**
     * When visibility is set to [.VISIBLE] or [.INVISIBLE],
     * [.inflate] is invoked and this StubbedView is replaced in its parent
     * by the inflated layout resource. After that calls to this function are passed
     * through to the inflated view.
     *
     * @param visibility One of [.VISIBLE], [.INVISIBLE], or [.GONE].
     *
     * @see .inflate
     */
    override fun setVisibility(visibility: Int) {
        if (mInflatedViewRef != null) {
            val view = mInflatedViewRef!!.get()
            if (view != null) {
                view.visibility = visibility
            } else {
                throw IllegalStateException("setVisibility called on un-referenced view")
            }
        } else {
            super.setVisibility(visibility)
            if (visibility == VISIBLE || visibility == INVISIBLE) {
                inflate()
            }
        }
    }

    /** @hide
     */
    fun setVisibilityAsync(visibility: Int): Runnable? {
        return if (visibility == VISIBLE || visibility == INVISIBLE) {
            val parent = parent as ViewGroup
            ViewReplaceRunnable(inflateViewNoAdd(parent))
        } else {
            null
        }
    }

    private fun inflateViewNoAdd(parent: ViewGroup): FrameLayout {
        return FrameLayout(parent.context).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    fun replaceSelfWithView(view: View, parent: ViewGroup?) {
        parent?:return
        val index = parent.indexOfChild(this)
        parent.removeViewInLayout(this)
        val layoutParams = layoutParams
        if (layoutParams != null) {
            parent.addView(view, index, layoutParams)
        } else {
            parent.addView(view, index)
        }
    }

    /**
     * Inflates the layout resource identified by [.getLayoutResource]
     * and replaces this StubbedView in its parent by the inflated layout resource.
     *
     * @return The inflated layout resource.
     */
    fun inflate(): FrameLayout {
        val viewParent = parent
        return if (viewParent != null && viewParent is ViewGroup) {
            inflateViewNoAdd(viewParent).also {
                replaceSelfWithView(it, viewParent)
                mInflatedViewRef = WeakReference(it)
            }
        } else {
            throw IllegalStateException("ViewStub must have a non-null ViewGroup viewParent")
        }
    }


    /** @hide
     */
    inner class ViewReplaceRunnable(private val view: View) : Runnable {
        override fun run() {
            replaceSelfWithView(view, parent as ViewGroup?)
        }
    }
}