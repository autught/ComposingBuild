import com.autught.plugin.BuildConfig
import com.autught.plugin.Libraries

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.autught.version-plugin") apply false
}

android {
    namespace = "com.autught.lib"
    compileSdk = BuildConfig.compileSdkVersion

    defaultConfig {
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}

dependencies {
//    implementation("androidx.core:core-ktx:1.8.0")
    implementation(Libraries.appcompat)
//    implementation("com.google.android.material:material:1.5.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}