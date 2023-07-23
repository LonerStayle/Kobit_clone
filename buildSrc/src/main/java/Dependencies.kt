object Dependencies {


    const val COMPILE_SDK = 33
    const val MIN_SDK = 30
    const val TARGET_SDK = 33

    const val JVM_TARGET = "17"
    const val KOTLIN_COMPLIER_EXTENSION = "1.3.2"

    const val KOTLIN = "1.7.20"
    const val ANDROID = "8.0.2"

    object Junit {
        const val Junit = "junit:junit:4.13.2"
    }

    object AndroidX {
        const val Core = "androidx.core:core-ktx:1.10.1"
        const val LifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"

        private const val COMPOSE_VERSION = "1.3.3"
        const val Compose_Core = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val Compose_Graphics = "androidx.compose.ui:ui-graphics:$COMPOSE_VERSION"
        const val Compose_Preview = "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION"
        const val Compose_Material3 = "androidx.compose.material3:material3:1.0.1"
        const val Compose_Activty = "androidx.activity:activity-compose:1.7.2"
        const val Compose_Navigation = "androidx.navigation:navigation-compose:2.6.0"
        const val Compose_Hilt_Navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

        private const val PAGING_VERSION = "3.1.1"
        const val Paging_Runtime = "androidx.paging:paging-runtime:$PAGING_VERSION"
        const val Paging_Common = "androidx.paging:paging-common:$PAGING_VERSION"

        private const val DATA_STORE_VERSION = "1.0.0"
        const val DataStore = "androidx.datastore:datastore:$DATA_STORE_VERSION"
        const val DataStore_Core = "androidx.datastore:datastore-core:$DATA_STORE_VERSION"
    }

    object accompanistPager {
        private const val VERSION = "0.28.0"
        const val Core = "com.google.accompanist:accompanist-pager:$VERSION"
        const val Indicators = "com.google.accompanist:accompanist-pager-indicators:$VERSION"
    }

    object Retrofit2 {
        private const val VERSION = "2.9.0"
        const val Core = "com.squareup.retrofit2:retrofit:$VERSION"
        const val Moshi = "com.squareup.retrofit2:converter-moshi:$VERSION"
    }

    object OkHttp3 {
        private const val VERSION = "4.11.0"
        const val Core = "com.squareup.okhttp3:okhttp:$VERSION"
        const val Logging = "com.squareup.okhttp3:logging-interceptor:$VERSION"
    }

    object Room {
        private const val VERSION = "2.5.0"
        const val Runtime = "androidx.room:room-runtime:$VERSION"
        const val Compiler = "androidx.room:room-compiler:$VERSION"
        const val Ktx = "androidx.room:room-ktx:$VERSION"
    }

    object Hilt {
        const val VERSION = "2.44"
        const val Core = "com.google.dagger:hilt-android:$VERSION"
        const val Compiler = "com.google.dagger:hilt-compiler:$VERSION"
        const val GradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$VERSION"
    }

    object Coil {
        private const val VERSION = "2.4.0"
        const val Core = "io.coil-kt:coil:$VERSION"
        const val Compose = "io.coil-kt:coil-compose:$VERSION"
    }
}