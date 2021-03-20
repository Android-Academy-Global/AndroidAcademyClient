// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    mavenCentral()
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.1.3")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.4")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.29.1-alpha")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

allprojects {
  repositories {
    mavenCentral()
    google()
    jcenter()
  }
}

task<Delete>("clean") {
  delete(rootProject.buildDir)
}