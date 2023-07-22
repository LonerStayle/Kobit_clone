plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(Dependencies.Junit.Junit)
    api(kotlin("reflect"))
    implementation(Dependencies.AndroidX.Paging_Common)
    implementation(Dependencies.AndroidX.DataStore_Core)
    implementation("javax.inject:javax.inject:1")

}


