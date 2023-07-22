plugins {
    id("com.android.application") version(Dependencies.ANDROID) apply false
    id("com.android.library") version(Dependencies.ANDROID) apply false
    id("org.jetbrains.kotlin.android") version(Dependencies.KOTLIN) apply false
    id("org.jetbrains.kotlin.jvm") version(Dependencies.KOTLIN) apply false
    id("com.google.dagger.hilt.android") version(Dependencies.Hilt.VERSION) apply false

}
