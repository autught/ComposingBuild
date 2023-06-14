import com.autught.plugin.BuildConfig
import com.autught.plugin.Libraries
import com.autught.plugin.androidAnko
import com.autught.plugin.androidDb
import com.autught.plugin.androidDi
import com.autught.plugin.androidNet
import com.autught.plugin.androidTest

plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.application")
    id("com.autught.version-plugin") apply false
    id("dagger.hilt.android.plugin")
}

android {
    namespace = BuildConfig.appId
    compileSdk = BuildConfig.compileSdkVersion


    defaultConfig {
        applicationId = BuildConfig.appId
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "BASE_URL", BuildConfig.baseUrl)

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
    }

//    packaging {
//        resources {
//            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
//        }
//    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Libraries.glide)
    implementation(Libraries.appcompat)
    implementation(Libraries.activitykt)
    implementation(Libraries.fragmentKt)
    implementation(Libraries.datastore)
    androidDi()
    androidDb()
    androidNet()
    androidAnko()
    androidTest()
}