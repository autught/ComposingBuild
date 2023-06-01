package com.autught.plugin

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.androidTest(){
    "testImplementation"(Libraries.junit)
    "androidTestImplementation"(Libraries.testJunit)
    "androidTestImplementation"(Libraries.testEspresso)
}