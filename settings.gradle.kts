import java.io.FileInputStream
import java.util.Properties

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

            url = uri("https://maven.goma-cms.org/repository/nimmsta-core-release/")
            credentials {
                val p = Properties()
                p.load(FileInputStream("local.properties"))

                //Use your NIMMSTA access credentials. In this case set them in the local.properties file.
                username = p["nimmsta.username"].toString()
                password = p["nimmsta.password"].toString()
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
