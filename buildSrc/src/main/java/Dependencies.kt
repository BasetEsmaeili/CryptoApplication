object Dependencies {

    private const val android_gradle_version = "4.2.1"
    const val kotlin_version = "1.5.10"
    private const val lifecycleExtensions_version = "2.4.0-alpha02"
    private const val appCompat_version = "1.4.0-alpha02"
    private const val legacy_version = "1.0.0"
    private const val ktx_version = "1.6.0-rc01"
    private const val fragment_version = "1.4.0-alpha03"
    private const val test_rules_version = "1.1.0"
    private const val junit_ext_version = "1.1.1"
    private const val espresso_version = "3.3.0"
    private const val junit_version = "4.12"
    private const val android_core_test_version = "1.3.0"
    private const val robolectric_version = "4.3.1"
    private const val room_version = "2.4.0-alpha03"
    private const val recyclerview_version = "1.2.1"
    private const val lottie_version = "3.7.0"
    private const val timber_version = "4.7.1"
    private const val navigation_version = "2.4.0-alpha03"
    private const val material_version = "1.4.0-rc01"
    private const val constraint_layout_version = "2.1.0-beta02"
    private const val retrofit_version = "2.9.0"
    private const val okhttp_version = "4.9.0"
    private const val dagger_version = "2.37"
    private const val truth_version = "1.1.3"
    private const val core_test_version = "2.1.0"
    private const val coroutines_test_version = "1.5.0"
    private const val coroutines_version = "1.5.0"
    private const val truth_android_version = "1.0.0"
    private const val mockito_kotlin_version = "3.2.0"
    private const val persian_date_version = "1.1"
    private const val serialization_version = "1.2.1"
    private const val serialization_converter_version = "0.8.0"
    private const val work_version = "2.7.0-alpha04"
    private const val paging_version = "3.1.0-alpha01"
    private const val leakcanary_version = "2.7"
    private const val glide_version = "4.12.0"

    object Android {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.2"
        const val minSdkVersion = 21
        const val targetSdkVersion = 30
    }

    object Project {
        const val applicationId = "com.baset.crypto.trader"
        const val versionCode = 1
        const val versionName = "1.0.0"
        const val isMinifyEnabled = true
        const val isProguardUploadEnabled = true
        const val isZipAlignEnabled = true
        const val isShrinkResourcesEnabled = true
        const val isUseVectorDrawables = true
        const val isJniDebugEnabled = false
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val isLinkCheckEnabled = true
        const val isReleaseLoggingEnabled = false
        const val isDebugLoggingEnabled = true
        const val isCorrectErrorTypes = true
        const val isDisableVersionCheck = false
        const val isDataBindingEnabled = true
    }

    object Kotlin {
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version"
    }

    object ProGuards {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguards =  "proguard-rules.pro"
        const val multidexProguards = "multidex-config.pro"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$android_gradle_version"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version"
        const val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val android = "android"
        const val androidExtensions = "android.extensions"
        const val kotlin = "kotlin"
        const val kapt = "kapt"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val serialization = "kotlinx-serialization"
        const val javaLibrary = "java-library"
        const val androidLibrary = "com.android.library"
    }

    object Repositories {
        const val google = "https://maven.google.com"
        const val jitpack = "https://jitpack.io"
    }

    object AndroidSupport {
        const val appCompat = "androidx.appcompat:appcompat:$appCompat_version"
        const val legacy = "androidx.legacy:legacy-support-v4:$legacy_version"
        const val fragment = "androidx.fragment:fragment-ktx:$fragment_version"
        const val recyclerview = "androidx.recyclerview:recyclerview:$recyclerview_version"
    }

    object ArchitectureComponents {
        const val liveData_extensions = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleExtensions_version"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleExtensions_version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleExtensions_version"
        const val core_ktx = "androidx.core:core-ktx:$ktx_version"
        const val room_runtime = "androidx.room:room-runtime:$room_version"
        const val room_compiler = "androidx.room:room-compiler:$room_version"
        const val room_ktx = "androidx.room:room-ktx:$room_version"
        const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$navigation_version"
        const val work_manager = "androidx.work:work-runtime-ktx:$work_version"
        const val paging = "androidx.paging:paging-runtime:$paging_version"
    }

    object TestDevelopment {
        const val test_runner = "androidx.test:runner:$test_rules_version"
        const val test_rules = "androidx.test:rules:$test_rules_version"
        const val junit_ext = "androidx.test.ext:junit:$junit_ext_version"
        const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"
        const val junit = "junit:junit:$junit_version"
        const val android_core_test = "androidx.test:core-ktx:$android_core_test_version"
        const val junit_ext_ktx = "androidx.test.ext:junit-ktx:$junit_ext_version"
        const val robolectric = "org.robolectric:robolectric:$robolectric_version"
        const val truth_android = "androidx.test.ext:truth:$truth_android_version"
        const val truth = "com.google.truth:truth:$truth_version"
        const val arch_core_test = "androidx.arch.core:core-testing:$core_test_version"
        const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_test_version"
        const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:$mockito_kotlin_version"
    }

    object ThirdParty {
        const val lottie = "com.airbnb.android:lottie:$lottie_version"
        const val timber = "com.jakewharton.timber:timber:$timber_version"
        const val persian_date = "com.github.samanzamani:PersianDate:$persian_date_version"
        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:$leakcanary_version"
        const val glide = "com.github.bumptech.glide:glide:$glide_version"
        const val glide_compiler = "com.github.bumptech.glide:compiler:$glide_version"
    }

    object Design {
        const val material = "com.google.android.material:material:$material_version"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val serialization_converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$serialization_converter_version"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:$dagger_version"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:$dagger_version"
    }

    object Excludes {
        const val coroutines_debug = "DebugProbesKt.bin"
    }

    object BuildVariants {
        const val debug = "debug"
        const val release = "release"
    }
}