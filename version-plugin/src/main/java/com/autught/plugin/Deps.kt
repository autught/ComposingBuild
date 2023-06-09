package com.autught.plugin


object Versions {
    const val junit = "4.13.2"
    const val test_junit = "1.1.3"
    const val test_espresso = "3.4.0"

    const val appcompat = "1.4.1"
    const val activityKt = "1.7.2"
    const val fragmentKt = "1.5.7"
    const val datastore = "1.0.0"
    const val hilt = "2.44"
    const val room = "2.3.0"

    const val glide = "4.15.1"

    const val retrofit = "2.9.0"
    const val moshi = "1.15.0"
    const val okhttp = "4.10.0"

    const val splitties = "3.0.0"
}

object Libraries {
    //test
    const val junit = "junit:junit:${Versions.junit}"
    const val testJunit = "androidx.test.ext:junit:${Versions.test_junit}"
    const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"

    //support
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val activitykt = "androidx.activity:activity-ktx:${Versions.activityKt}"
    const val fragmentKt = "androidx.fragment:fragment-ktx:${Versions.fragmentKt}"
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    //hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    //room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKt = "androidx.room:room-ktx:${Versions.room}"

    //splitties
    const val splittieCtx = "com.louiscad.splitties:splitties-appctx:${Versions.splitties}"
    const val splittieView = "com.louiscad.splitties:splitties-views:${Versions.splitties}"
    const val splittieRv =
        "com.louiscad.splitties:splitties-views-recyclerview:${Versions.splitties}"
    const val splittieDSL = "com.louiscad.splitties:splitties-views-dsl:${Versions.splitties}"
    const val splittieDSLAppcompat =
        "com.louiscad.splitties:splitties-views-dsl-appcompat:${Versions.splitties}"
    const val splittieDSLConstraint =
        "com.louiscad.splitties:splitties-views-dsl-constraintlayout:${Versions.splitties}"

    //image
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //net
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLog = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshiConvert = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}
