object Config {
    // Android config
    const val androidBuildTools = "31.0.0"
    const val androidMinSdk = 21
    const val androidTargetSdk = 31
    const val androidCompileSdk = 31
    const val applicationId = "com.github.mik629.android.fundamentals"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.5.31"
    const val activityCompose = "1.3.1"
    const val compose = "1.0.5"
    const val vmCompose = "2.4.0-rc01"
    const val navigationCompose = "2.4.0-alpha10"
    const val hiltNavigationCompose = "1.0.0-alpha03"

    //Plugins
    const val androidToolsPlugin = "7.0.1"

    // Android libraries


    // third party libs
    const val coilVersion = "1.3.2"
    const val glideComposeVersion = "1.3.6"

    const val daggerVersion = "2.39.1"

    // testing
}

object Plugins {
    const val androidPlugin = "android"
    const val appPlugin = "com.android.application"
    const val kapt = "kapt"
    const val hiltPlugin = "dagger.hilt.android.plugin"
    const val navigationSafeArgsPlugin = "androidx.navigation.safeargs.kotlin"
    const val serializationPlugin = "plugin.serialization"
}

object Libs {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.compose}"
    const val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val vmCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.vmCompose}"

    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.daggerVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerVersion}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    const val glideComposeVersion = "com.github.skydoves:landscapist-glide:${Versions.glideComposeVersion}"
}