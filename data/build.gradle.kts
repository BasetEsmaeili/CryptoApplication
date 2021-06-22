import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.serialization)
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
        val api_key = gradleLocalProperties(rootDir).getProperty("api_key")
        buildConfigField("String", "API_KEY", "\"$api_key\"")
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
        exclude(Dependencies.Excludes.meta_inf_2)
        exclude(Dependencies.Excludes.meta_inf_2_1)
        exclude(Dependencies.Excludes.attach_hotspot_windows)
        exclude(Dependencies.Excludes.licenses)
    }

    kapt {
        correctErrorTypes = Dependencies.Project.isCorrectErrorTypes
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinStd)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.ArchitectureComponents.liveData_extensions)
    androidTestImplementation(Dependencies.TestDevelopment.test_runner)
    androidTestImplementation(Dependencies.TestDevelopment.test_rules)
    androidTestImplementation(Dependencies.TestDevelopment.espresso)
    androidTestImplementation(Dependencies.TestDevelopment.mockito_kotlin)
    androidTestImplementation(Dependencies.TestDevelopment.truth)
    androidTestImplementation(Dependencies.TestDevelopment.truth_android)
    androidTestImplementation(Dependencies.TestDevelopment.coroutines_test)
    androidTestImplementation(Dependencies.TestDevelopment.android_core_test)
    androidTestImplementation(Dependencies.TestDevelopment.arch_core_test)
    androidTestImplementation(Dependencies.TestDevelopment.junit_ext)
    testImplementation(Dependencies.TestDevelopment.junit_ext)
    testImplementation(Dependencies.TestDevelopment.android_core_test)
    testImplementation(Dependencies.TestDevelopment.truth)
    testImplementation(Dependencies.TestDevelopment.arch_core_test)
    testImplementation(Dependencies.TestDevelopment.coroutines_test)
    testImplementation(Dependencies.TestDevelopment.mockito_kotlin)
    implementation(Dependencies.ThirdParty.timber)
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.dagger_compiler)
    implementation(Dependencies.OkHttp.okhttp)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.serialization_converter)
    implementation(Dependencies.ArchitectureComponents.room_runtime)
    implementation(Dependencies.ArchitectureComponents.room_ktx)
    kapt(Dependencies.ArchitectureComponents.room_compiler)
    implementation(project(Dependencies.Modules.domain))
}