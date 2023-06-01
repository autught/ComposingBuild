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

dependencies {

}

