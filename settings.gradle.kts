/*pluginManagement {
    includeBuild("version-plugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}*/

rootProject.name = "Chat"
include(":app")
includeBuild("version-plugin")
include(":lib")
