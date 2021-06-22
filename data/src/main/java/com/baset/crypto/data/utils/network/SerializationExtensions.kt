package com.baset.crypto.data.utils.network

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> String?.toSerializableModel(): T? {
    if (this.isNullOrEmpty()) return null
    return Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }.decodeFromString<T>(this)
}