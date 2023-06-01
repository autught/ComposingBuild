package com.autught.plugin


object Versions {
    const val junit = "4.13.2"
    const val test_junit="1.1.3"
    const val test_espresso="3.4.0"

    const val lifecycle="2.5.1"
}

object Libraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val testJunit = "androidx.test.ext:junit:${Versions.test_junit}"
    const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"

    const val lifecycleRuntime="androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}
