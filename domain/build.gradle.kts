plugins {
    id(Dependencies.Plugins.javaLibrary)
    id(Dependencies.Plugins.kotlin)
    kotlin(Dependencies.Plugins.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinStd)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.dagger_compiler)
    testImplementation(Dependencies.TestDevelopment.junit)
    testImplementation(Dependencies.TestDevelopment.coroutines_test)
    testImplementation(Dependencies.TestDevelopment.mockito_kotlin)
    testImplementation(Dependencies.TestDevelopment.truth)
}