package com.autught.plugin

import org.gradle.kotlin.dsl.DependencyHandlerScope


const val imp = "implementation"
const val kapt = "kapt"
fun DependencyHandlerScope.androidTest() {
    "testImplementation"(Libraries.junit)
    "androidTestImplementation"(Libraries.testJunit)
    "androidTestImplementation"(Libraries.testEspresso)
}

fun DependencyHandlerScope.androidNet() {
    imp(Libraries.retrofit)
    imp(Libraries.okhttp)
    imp(Libraries.okhttpLog)
    imp(Libraries.moshi)
    imp(Libraries.moshiConvert)
    kapt(Libraries.moshiKapt)
}

fun DependencyHandlerScope.androidDi() {
    imp(Libraries.hilt)
    kapt(Libraries.hiltCompiler)
}

fun DependencyHandlerScope.androidAnko() {
    imp(Libraries.splittieCtx)
    imp(Libraries.splittieView)
    imp(Libraries.splittieRv)
    imp(Libraries.splittieDSL)
    imp(Libraries.splittieDSLAppcompat)
    imp(Libraries.splittieDSLConstraint)
}

fun DependencyHandlerScope.androidDb() {
    imp(Libraries.room)
    imp(Libraries.roomKt)
    kapt(Libraries.roomCompiler)
}