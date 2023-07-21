plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "kr.loner.data"
    compileSdk = Dependencies.COMPILE_SDK

    defaultConfig {
        minSdk = Dependencies.MIN_SDK

        targetSdk = Dependencies.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Dependencies.JVM_TARGET
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.Retrofit2.Core)
    implementation(Dependencies.Retrofit2.Moshi)
    annotationProcessor(Dependencies.Room.Compiler)
    kapt(Dependencies.Room.Compiler)
    implementation(Dependencies.Room.Runtime)
    implementation(Dependencies.AndroidX.Paging_Runtime)

    implementation(Dependencies.Hilt.Core)
    kapt(Dependencies.Hilt.Compiler)

    implementation(Dependencies.AndroidX.DataStore)
}