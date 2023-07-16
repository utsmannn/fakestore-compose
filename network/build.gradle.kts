plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    val ktorVersion = "2.3.2"


    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "network"
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
            }
        }

        getByName("androidMain").dependencies {
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }

        getByName("iosMain").dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
    }
}

android {
    namespace = "com.utsman.network"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}