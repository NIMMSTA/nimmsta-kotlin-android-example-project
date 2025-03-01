import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {

            url = uri("https://maven.nimmsta.com/repository/nimmsta-core-release/")
            credentials {
                //Use your NIMMSTA access credentials. In this case set them in the local.properties file.
                username = localProperties.getProperty("nimmsta.username")
                password = localProperties.getProperty("nimmsta.password")
            }
            metadataSources {
                mavenPom()
                artifact()
            }
        }
    }
}

rootProject.name = "nimmsta-kotlin-android-example-project"
include(":app")
