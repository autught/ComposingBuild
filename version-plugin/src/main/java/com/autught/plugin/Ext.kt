package com.autught.plugin

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.androidTest() {
    "testImplementation"(Libraries.junit)
    "androidTestImplementation"(Libraries.testJunit)
    "androidTestImplementation"(Libraries.testEspresso)
}

fun DependencyHandlerScope.androidNet() {
    "implementation"(Libraries.retrofit)
    "implementation"(Libraries.okhttp)
    "implementation"(Libraries.moshi)
}

fun DependencyHandlerScope.androidDI() {
    "implementation"(Libraries.hilt)
    "kapt"(Libraries.hiltCompiler)
}