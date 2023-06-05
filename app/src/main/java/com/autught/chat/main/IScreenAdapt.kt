package com.autught.chat.main

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources

interface IScreenAdapt {

    fun adaptOrientation():Int

    fun adaptDesign():Int

    fun adapt(
        activity: Activity,
        application: Application,
        orientation: Int,
        designSize: Int
    ) {
        val systemDisplayMetrics = Resources.getSystem().displayMetrics

        //竖屏状态下以设计图宽360dp适配
        val targetDensity: Float = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            systemDisplayMetrics.widthPixels.toFloat() / designSize
        } else {
            systemDisplayMetrics.heightPixels.toFloat() / designSize
        }
        val targetScaledDensity: Float =
            targetDensity * (systemDisplayMetrics.scaledDensity / systemDisplayMetrics.density)
        val targetDensityDpi: Int = (160 * targetDensity).toInt()

        application.resources.displayMetrics.apply {
            density = targetDensity
            scaledDensity = targetScaledDensity
            densityDpi = targetDensityDpi
        }

        activity.resources.displayMetrics.apply {
            density = targetDensity
            scaledDensity = targetScaledDensity
            densityDpi = targetDensityDpi
        }
    }

    fun cancel(activity: Activity, application: Application) {
        val systemDisplayMetrics = Resources.getSystem().displayMetrics
        application.resources.displayMetrics.apply {
            density = systemDisplayMetrics.density
            scaledDensity = systemDisplayMetrics.scaledDensity
            densityDpi = systemDisplayMetrics.densityDpi
        }

        activity.resources.displayMetrics.apply {
            density = systemDisplayMetrics.density
            scaledDensity = systemDisplayMetrics.scaledDensity
            densityDpi = systemDisplayMetrics.densityDpi
        }
    }

    fun isAdapt(activity: Activity): Boolean {
        val systemDisplayMetrics = Resources.getSystem().displayMetrics
        val activityDisplayMetrics = activity.resources.displayMetrics
        return systemDisplayMetrics.density != activityDisplayMetrics.density
    }
}