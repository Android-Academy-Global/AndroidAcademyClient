buildscript {
  val kotlinVersion by extra("1.4.31")
  val hiltVersion by extra("2.33-beta")
  val navigationVersion by extra("2.3.4")
    val kotlin_version by extra("1.4.31")
    repositories {
    mavenCentral()
    google()
    jcenter()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
    classpath("com.android.tools.build:gradle:4.1.3")
    classpath ("com.google.dagger:hilt-android-gradle-plugin:${hiltVersion}")
    classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${navigationVersion}")

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