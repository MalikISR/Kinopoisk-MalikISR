// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://storage.googleapis.com/r8-releases/raw")
        }

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password =
                    "sk.eyJ1Ijoiamh1dXVpayIsImEiOiJjbHBmdGVyNW4xcXA1MmhybDJsY2FodGdtIn0.M4B2ULGkzYdTJKBmnOcWOA"
            }
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("com.android.tools:r8:8.5.35")
    }

}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.2" apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}