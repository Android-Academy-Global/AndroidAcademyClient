plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
  id("androidx.navigation.safeargs.kotlin")
}

android {
  compileSdkVersion(30)
  buildToolsVersion = "30.0.3"

  defaultConfig {
    applicationId = "com.academy.android"
    minSdkVersion(21)
    targetSdkVersion(30)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {

  //core
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
  implementation("androidx.core:core-ktx:1.3.2")

  //ui
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("com.google.android.material:material:1.3.0")
  implementation("androidx.constraintlayout:constraintlayout:2.0.4")

  //navigation
  implementation("androidx.navigation:navigation-fragment-ktx:2.3.4")
  implementation("androidx.navigation:navigation-ui-ktx:2.3.4")

  //lifecycle
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")

  //di
  kapt("com.google.dagger:hilt-compiler:2.29-alpha")
  implementation("com.google.dagger:hilt-android:2.29-alpha")

  //concurrency
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

  //image downloading
  implementation("io.coil-kt:coil:1.1.1")

  //logging
  implementation("com.jakewharton.timber:timber:4.7.1")

  //db
  implementation("androidx.room:room-runtime:2.2.6")
  kapt("androidx.room:room-compiler:2.2.6")
  implementation("androidx.room:room-ktx:2.2.6")
  testImplementation("androidx.room:room-testing:2.2.6")

  //testing
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.2")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}