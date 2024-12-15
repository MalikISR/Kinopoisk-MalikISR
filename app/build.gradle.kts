plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.kinopoiskmalik"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kinopoiskmalik"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"8e448f2a-2a2b-4821-a456-fa1e44685ac4\"",
            )
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://kinopoiskapiunofficial.tech\"",
            )
        }
        debug {
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"8e448f2a-2a2b-4821-a456-fa1e44685ac4\"",
            )
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://kinopoiskapiunofficial.tech\"",
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.github.compose.datetime)
    implementation(libs.androidx.lifecycle.viewModel.compose)
    implementation(libs.androidx.lifecycle.viewModel.ktx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.activity)
    implementation(libs.play.services.auth.api.phone)
    androidTestImplementation(libs.androidx.eespresso.core)
    debugImplementation(libs.androidx.compose.tooling)
    debugImplementation(libs.androidx.compose.toolingPreview)
    implementation(libs.androidx.compose.lifecycl.runtime)

    //Junit
    testImplementation(libs.junit.ktx)

    //Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundationLayout)
    implementation(libs.androidx.compose.iconExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    debugImplementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.toolingPreview)
    debugImplementation(libs.androidx.compose.toolingPreview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.utils)
    implementation(libs.androidx.compose.uiTestManifest)
    implementation(libs.androidx.compose.uiTest)

    //Hilt
    implementation(libs.google.hilt.android)
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.startup)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)
    kapt(libs.google.hilt.android.compiler)
    kaptAndroidTest(libs.google.hilt.compiler)

    //Retrofit
    implementation(libs.squareup.okhttp3.interceptor)
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.squareup.retrofit2.converter.serialization)
    implementation(libs.squareup.retrofit2)

    //ImageLoader
    implementation(libs.coil.compose)

    //System UI Controller
    implementation(libs.google.accompanist.systemUiController)

    //Splash screen
    implementation(libs.androidx.splashscreen)

    //Test
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
}