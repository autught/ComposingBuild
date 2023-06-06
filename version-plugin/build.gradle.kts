plugins {
    `kotlin-dsl`
}

gradlePlugin{
    plugins {
        register("versionPlugin"){
            id="com.autught.version-plugin"
            implementationClass = "com.autught.plugin.VersionPlugin"
        }
    }
}

repositories {
    //google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
}

