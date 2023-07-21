plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{
    api(Dependencies.Junit.Junit)
    implementation(Dependencies.AndroidX.Paging_Common)
    implementation(Dependencies.AndroidX.DataStore_Core)
}

