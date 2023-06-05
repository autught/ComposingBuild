package com.autught.chat.views

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import java.lang.ref.WeakReference

class LinearViewStub(context: Context) : View(context) {


    private var mInflatedViewRef: WeakReference<View>? = null


    init {
        visibility = GONE
        setWillNotDraw(true)
    }






    /**
     * Set [LayoutInflater] to use in [.inflate], or `null`
     * to use the default.
     */
    fun setLayoutInflater(inflater: LayoutInflater?) {
        mInflater = inflater
    }

    /**
     * Get current [LayoutInflater] used in [.inflate].
     */
    fun getLayoutInflater(): LayoutInflater? {
        return mInflater
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    override fun draw(canvas: Canvas?) {}

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
    @android.view.RemotableViewMethod(asyncImpl = "setVisibilityAsync")
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

    private fun inflateViewNoAdd(parent: ViewGroup): View {
        val factory: LayoutInflater
        factory = if (mInflater != null) {
            mInflater
        } else {
            LayoutInflater.from(mContext)
        }
        val view = factory.inflate(mLayoutResource, parent, false)
        if (mInflatedId != NO_ID) {
            view.id = mInflatedId
        }
        return view
    }

    private fun replaceSelfWithView(view: View, parent: ViewGroup) {
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
    fun inflate(): View? {
        val viewParent = parent
        return if (viewParent != null && viewParent is ViewGroup) {
            if (mLayoutResource != 0) {
                val parent = viewParent
                val view = inflateViewNoAdd(parent)
                replaceSelfWithView(view, parent)
                mInflatedViewRef = WeakReference(view)
                if (mInflateListener != null) {
                    mInflateListener!!.onInflate(this, view)
                }
                view
            } else {
                throw IllegalArgumentException("ViewStub must have a valid layoutResource")
            }
        } else {
            throw IllegalStateException("ViewStub must have a non-null ViewGroup viewParent")
        }
    }

    /**
     * Specifies the inflate listener to be notified after this ViewStub successfully
     * inflated its layout resource.
     *
     * @param inflateListener The OnInflateListener to notify of successful inflation.
     *
     * @see android.view.ViewStub.OnInflateListener
     */
    fun setOnInflateListener(inflateListener: OnInflateListener?) {
        mInflateListener = inflateListener
    }

    /**
     * Listener used to receive a notification after a ViewStub has successfully
     * inflated its layout resource.
     *
     * @see android.view.ViewStub.setOnInflateListener
     */
    interface OnInflateListener {
        /**
         * Invoked after a ViewStub successfully inflated its layout resource.
         * This method is invoked after the inflated view was added to the
         * hierarchy but before the layout pass.
         *
         * @param stub The ViewStub that initiated the inflation.
         * @param inflated The inflated View.
         */
        fun onInflate(stub: ViewStub?, inflated: View?)
    }

    /** @hide
     */
    class ViewReplaceRunnable internal constructor(val view: View) : Runnable {
        override fun run() {
            replaceSelfWithView(view, getParent() as ViewGroup?)
        }
    }
}