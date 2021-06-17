plugins {
    id(Dependencies.Plugins.androidApplication)
    kotlin(Dependencies.Plugins.android)
    kotlin(Dependencies.Plugins.androidExtensions)
    kotlin(Dependencies.Plugins.kapt)
    id(Dependencies.Plugins.navigationSafeArgs)
}
android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    buildToolsVersion(Dependencies.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        applicationId = Dependencies.Project.applicationId
        versionCode = Dependencies.Project.versionCode
        versionName = Dependencies.Project.versionName
        testInstrumentationRunner = Dependencies.Project.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = Dependencies.Project.isUseVectorDrawables
    }

    lintOptions {
        isCheckReleaseBuilds = Dependencies.Project.isLinkCheckEnabled
        isAbortOnError = Dependencies.Project.isLinkCheckEnabled
    }

    dexOptions {
        javaMaxHeapSize = "8g"
        jumboMode = true
        preDexLibraries = true
        threadCount = 8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildTypes {
        getByName(Dependencies.BuildVariants.release) {
            isMinifyEnabled = Dependencies.Project.isMinifyEnabled
            isZipAlignEnabled = Dependencies.Project.isZipAlignEnabled
            isShrinkResources = Dependencies.Project.isShrinkResourcesEnabled
            isJniDebuggable = Dependencies.Project.isJniDebugEnabled
            proguardFiles(
                getDefaultProguardFile(Dependencies.ProGuards.proguardAndroidOptimize),
                Dependencies.ProGuards.proguards
            )
            multiDexKeepProguard = file(Dependencies.ProGuards.multidexProguards)
        }
        getByName(Dependencies.BuildVariants.debug) {
            isMinifyEnabled = false
            proguardFile("proguard-android.txt")
        }
    }

    buildFeatures {
        dataBinding = Dependencies.Project.isDataBindingEnabled
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.kotlinStd)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.coroutines_android)
    implementation(Dependencies.AndroidSupport.appCompat)
    implementation(Dependencies.AndroidSupport.legacy)
    implementation(Dependencies.AndroidSupport.fragment)
    implementation(Dependencies.AndroidSupport.recyclerview)
    kapt(Dependencies.AndroidSupport.databinding_compiler)
    implementation(Dependencies.Design.material)
    implementation(Dependencies.Design.constraint_layout)
    implementation(Dependencies.ArchitectureComponents.core_ktx)
    implementation(Dependencies.ArchitectureComponents.lifecycle_runtime)
    implementation(Dependencies.ArchitectureComponents.liveData_extensions)
    implementation(Dependencies.ArchitectureComponents.viewModel)
    implementation(Dependencies.ArchitectureComponents.navigation_fragment)
    implementation(Dependencies.ArchitectureComponents.navigation_ui)
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
    implementation(Dependencies.ThirdParty.lottie)
    implementation(Dependencies.ThirdParty.timber)
    implementation(Dependencies.ThirdParty.persian_date)
    debugImplementation(Dependencies.ThirdParty.leakcanary)
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.dagger_compiler)
    implementation(Dependencies.ThirdParty.glide)
    kapt(Dependencies.ThirdParty.glide_compiler)
}