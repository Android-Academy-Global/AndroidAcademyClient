buildscript {
    val navigationVersion by extra("2.3.4")
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
        classpath("com.android.tools.build:gradle:${Versions.androidToolsPlugin}")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${navigationVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}