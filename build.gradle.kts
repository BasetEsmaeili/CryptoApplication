// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        maven(Dependencies.Repositories.google)
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.androidGradle)
        classpath(Dependencies.BuildPlugins.kotlinGradle)
        classpath(Dependencies.BuildPlugins.kotlin_serialization)
        classpath(Dependencies.BuildPlugins.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        maven(Dependencies.Repositories.google)
        jcenter()
        mavenCentral()
        maven(Dependencies.Repositories.jitpack)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
