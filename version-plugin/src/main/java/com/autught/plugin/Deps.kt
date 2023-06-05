package com.autught.plugin


object Versions {
    const val junit = "4.13.2"
    const val test_junit = "1.1.3"
    const val test_espresso = "3.4.0"

    const val lifecycle = "2.5.1"
    const val glide = "4.15.1"

    const val retrofit = "2.9.0"
    const val moshi = "1.15.0"
    const val okhttp = "5.0.0-alpha.11"

    const val hilt = "2.44"
}

object Libraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val testJunit = "androidx.test.ext:junit:${Versions.test_junit}"
    const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //net
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler="com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}
