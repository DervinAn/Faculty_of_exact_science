plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.facultyofexactscience"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.facultyofexactscience"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        // ✅ Add your API base URL here (please confirm final value)
        buildConfigField("String", "BASE_URL", "\"http://192.168.1.13:8000/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // optional: enable extra logging flags if desired
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    // TV Compose
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material.v100)

    // Lifecycle + Activity
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Optional utilities you already had
    implementation(libs.androidx.tracing.perfetto.handshake)

    // ❌ Remove Volley & Transport if unused
    // implementation(libs.volley)
    // implementation(libs.transport.runtime)

    // ✅ KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)

    // ✅ Ktor HTTP client stack
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)                // Android engine
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)

    //Coil
    implementation(libs.coil.compose)
}
