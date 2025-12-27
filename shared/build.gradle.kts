import co.touchlab.skie.configuration.EnumInterop
import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuppressSkieWarning
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.touchlabSkie)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlinParcelize)
//    alias(libs.plugins.sqldelight)
}

buildkonfig {
    packageName = "com.iqbalwork"

    val secretPropertiesFile = rootProject.file("secret.properties")
    val secretProperties = Properties().apply {
        if (secretPropertiesFile.exists()) {
            load(secretPropertiesFile.inputStream())
        }
    }

    val githubUrl = secretProperties["github.url"] as? String ?: ""
    val githubToken = secretProperties["github.token"] as? String ?: ""

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", githubUrl)
        buildConfigField(STRING, "GITHUB_TOKEN", githubToken)
        buildConfigField(BOOLEAN, "DEBUG", "true")
    }

    defaultConfigs("production") {
        buildConfigField(STRING, "BASE_URL", githubUrl)
        buildConfigField(STRING, "GITHUB_TOKEN", githubToken)
        buildConfigField(BOOLEAN, "DEBUG", "false")
    }
}

skie {
    build {
        produceDistributableFramework()
        enableRelativeSourcePathsInDebugSymbols.set(true)
    }
    features {
        group {
            SuppressSkieWarning.NameCollision(true)
            coroutinesInterop.set(true)
            FlowInterop.Enabled(true)
            EnumInterop.Enabled(true)
            EnumInterop.LegacyCaseName(true)
            SealedInterop.Enabled(true)
        }
        isEnabled = true
    }
    swiftBundling {
        enabled = true
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            linkerOpts("-lsqlite3")
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.multiplatform.settings.no.arg)
            implementation(libs.skie.configuration.annotations)
            implementation(libs.koin.core)
            api(libs.napier) // Logging
            // Networking
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.inspektify.ktor3) // Network Logging
        }
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.coil.compose)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
            api(libs.coil.network.okhttp)
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.iqbalwork.kmpgithub.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
