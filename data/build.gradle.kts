plugins {
    id(Dependencies.Plugins.androidLibrary)
    kotlin(Dependencies.Plugins.android)
    kotlin(Dependencies.Plugins.kapt)
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    buildToolsVersion(Dependencies.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Project.versionCode
        versionName = Dependencies.Project.versionName
        testInstrumentationRunner = Dependencies.Project.testInstrumentationRunner
    }

    lintOptions {
        isCheckReleaseBuilds = Dependencies.Project.isLinkCheckEnabled
        isAbortOnError = Dependencies.Project.isLinkCheckEnabled
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    dexOptions {
        javaMaxHeapSize = "8g"
        jumboMode = true
        preDexLibraries = true
        threadCount = 8
    }

    buildTypes {
        getByName(Dependencies.BuildVariants.release) {
            isMinifyEnabled = Dependencies.Project.isMinifyEnabled
            isJniDebuggable = Dependencies.Project.isJniDebugEnabled
            proguardFiles(
                getDefaultProguardFile(Dependencies.ProGuards.proguardAndroidOptimize),
                Dependencies.ProGuards.proguards
            )
        }
        getByName(Dependencies.BuildVariants.debug) {
            isMinifyEnabled = false
            proguardFile("proguard-android.txt")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude(Dependencies.Excludes.coroutines_debug)
    }

    kapt {
        correctErrorTypes = Dependencies.Project.isCorrectErrorTypes
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinStd)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.dagger_compiler)
}