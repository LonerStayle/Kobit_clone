plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "kr.loner.kobit"
    compileSdk = Dependencies.COMPILE_SDK

    defaultConfig {
        applicationId = "kr.loner.kobit"
        minSdk = Dependencies.MIN_SDK
        targetSdk = Dependencies.TARGET_SDK
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Dependencies.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.KOTLIN_COMPLIER_EXTENSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(Dependencies.AndroidX.Core)
    implementation(Dependencies.AndroidX.LifeCycle)
    implementation(Dependencies.AndroidX.Compose_Activty)
    implementation(Dependencies.AndroidX.Compose_Core)
    implementation(Dependencies.AndroidX.Compose_Graphics)
    implementation(Dependencies.AndroidX.Compose_Preview)
    implementation(Dependencies.AndroidX.Compose_Material3)
    implementation(Dependencies.AndroidX.Compose_Navigation)
    implementation(Dependencies.AndroidX.Paging_Runtime)

    implementation(Dependencies.Hilt.Core)
    kapt(Dependencies.Hilt.Compiler)

    implementation(Dependencies.Coil.Core)
    implementation(Dependencies.Coil.Compose)
}