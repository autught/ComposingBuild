import com.autught.plugin.BuildConfig
import com.autught.plugin.Libraries
import com.autught.plugin.androidDI
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
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", BuildConfig.baseUrl)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }


    buildFeatures {
        buildConfig = true
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Libraries.lifecycleRuntime)
    implementation(Libraries.glide)
    implementation("androidx.appcompat:appcompat:1.4.1")
//    implementation("androidx.core:core-ktx:+")
    androidDI()
    androidNet()
    androidTest()
}