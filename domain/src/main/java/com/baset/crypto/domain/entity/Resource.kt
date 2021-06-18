package com.baset.crypto.domain.entity

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String,
    val code: Long?
) {
    companion object {

        fun <T> success(msg: String, data: T?, code: Long? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, msg, code)
        }

        fun <T> error(msg: String, data: T?, code: Long? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }
    }
}
