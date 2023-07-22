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
        buildConfigField("String", "BASE_URL", "\"https://api.korbit.co.kr/v1/\"")
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.Retrofit2.Core)
    implementation(Dependencies.Retrofit2.Moshi)
    implementation(Dependencies.OkHttp3.Core)
    implementation(Dependencies.OkHttp3.Logging)


    annotationProcessor(Dependencies.Room.Compiler)
    kapt(Dependencies.Room.Compiler)
    implementation(Dependencies.Room.Runtime)
    implementation(Dependencies.AndroidX.Paging_Runtime)

    implementation(Dependencies.Hilt.Core)
    kapt(Dependencies.Hilt.Compiler)

    implementation(Dependencies.AndroidX.DataStore)

}