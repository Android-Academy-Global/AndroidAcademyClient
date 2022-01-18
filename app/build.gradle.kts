import Config.androidBuildTools
import Config.androidCompileSdk
import Config.androidMinSdk
import Config.androidTargetSdk

plugins {
    id(Plugins.appPlugin)
    kotlin(Plugins.androidPlugin)
    kotlin(Plugins.serializationPlugin)
    kotlin(Plugins.kapt)
    id(Plugins.hiltPlugin)
    id(Plugins.navigationSafeArgsPlugin)
}

android {
    compileSdk = androidCompileSdk
    buildToolsVersion = androidBuildTools

    defaultConfig {
        applicationId = "com.academy.android"
        minSdk = androidMinSdk
        targetSdk = androidTargetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.compose
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    // auth
    implementation(Libs.playServicesAuth)

    // Core
    implementation("androidx.core:core-ktx:1.3.2")

    // UI
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.0")

    // Activity KTX
    implementation("androidx.activity:activity-ktx:1.3.0-alpha06")

    // Fragment KTX
    implementation("androidx.fragment:fragment-ktx:1.3.2")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra["navigationVersion"]}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra["navigationVersion"]}")

    // Lifecycle
    val lifecycleVersion = "2.3.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // DI
    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)
    implementation(Libs.hiltNavigationCompose)
    val androidxHilt = "1.0.0-alpha03"
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$androidxHilt")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")

    // Concurrency
    val coroutinesVersion = "1.4.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Image downloading
    implementation("io.coil-kt:coil:1.1.1")

    // Logging
    implementation("com.jakewharton.timber:timber:4.7.1")

    //SharedPreference
    implementation (Libs.dataStore)

    // DB
    val roomVersion = "2.2.6"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // WorkManager
    val workVersion = "2.5.0"
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    androidTestImplementation("androidx.work:work-testing:$workVersion")

    // ViewBindingPropertyDelegate
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.4.4")

    // compose
    implementation(Libs.activityCompose)
    implementation(Libs.vmCompose)
    implementation(Libs.composeCompiler)
    implementation(Libs.composeFoundation)
    implementation(Libs.composeMaterial)
    implementation(Libs.composeUI)
    implementation(Libs.composeTooling)
    implementation(Libs.coil)
    implementation(Libs.glideComposeVersion)

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}