package com.baset.crypto.data.utils

import timber.log.Timber

inline fun <reified T> errorLog(throwable: Throwable) {
    Timber.tag(T::class.simpleName)
    Timber.e(throwable)
}

fun errorLog(tag: String? = null, throwable: Throwable) {
    Timber.tag(tag ?: "Crypto App")
    Timber.e(throwable)
}

inline fun <reified T> errorLog(exception: Exception) {
    Timber.tag(T::class.simpleName)
    Timber.e(exception)
}

fun errorLog(tag: String? = null, exception: Exception) {
    Timber.tag(tag ?: "Crypto App")
    Timber.e(exception)
}

inline fun <reified T> errorLog(message: String) {
    Timber.tag(T::class.simpleName)
    Timber.e(message)
}

fun errorLog(tag: String? = null, message: String) {
    Timber.tag(tag ?: "Crypto App")
    Timber.e(message)
}

inline fun <reified T> verboseLog(message: String) {
    Timber.tag(T::class.simpleName)
    Timber.v(message)
}

fun verboseLog(tag: String? = null, message: String) {
    Timber.tag(tag ?: "Crypto App")
    Timber.v(message)
}

inline fun <reified T> verboseLog(exception: Exception) {
    Timber.tag(T::class.simpleName)
    Timber.v(exception)
}

fun verboseLog(tag: String? = null, exception: Exception) {
    Timber.tag(tag ?: "Crypto App")
    Timber.v(exception)
}

inline fun <reified T> verboseLog(throwable: Throwable) {
    Timber.tag(T::class.simpleName)
    Timber.v(throwable)
}

fun verboseLog(tag: String? = null, throwable: Throwable) {
    Timber.tag(tag ?: "Crypto App")
    Timber.v(throwable)
}

inline fun <reified T> debugLog(message: String) {
    Timber.tag(T::class.simpleName)
    Timber.d(message)
}

fun debugLog(tag: String? = null, message: String) {
    Timber.tag(tag ?: "Crypto App")
    Timber.d(message)
}

inline fun <reified T> debugLog(throwable: Throwable) {
    Timber.tag(T::class.simpleName)
    Timber.d(throwable)
}

fun debugLog(tag: String? = null, throwable: Throwable) {
    Timber.tag(tag ?: "Crypto App")
    Timber.d(throwable)
}

inline fun <reified T> debugLog(exception: Exception) {
    Timber.tag(T::class.simpleName)
    Timber.d(exception)
}

fun debugLog(tag: String? = null, exception: Exception) {
    Timber.tag(tag ?: "Crypto App")
    Timber.d(exception)
}

fun infoLog(tag: String? = null, message: String) {
    Timber.tag(tag ?: "Crypto App")
    Timber.i(message)
}

inline fun <reified T> infoLog(message: String) {
    Timber.tag(T::class.simpleName)
    Timber.i(message)
}

inline fun <reified T> infoLog(throwable: Throwable) {
    Timber.tag(T::class.simpleName)
    Timber.i(throwable)
}

fun infoLog(tag: String? = null, throwable: Throwable) {
    Timber.tag(tag ?: "Crypto App")
    Timber.i(throwable)
}

inline fun <reified T> infoLog(exception: Exception) {
    Timber.tag(T::class.simpleName)
    Timber.i(exception)
}

fun infoLog(tag: String? = null, exception: Exception) {
    Timber.tag(tag ?: "Crypto App")
    Timber.i(exception)
}