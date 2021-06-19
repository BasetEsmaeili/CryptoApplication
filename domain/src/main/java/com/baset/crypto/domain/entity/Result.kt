package com.baset.crypto.domain.entity

data class Result<out T>(
    val status: Status,
    val errorType: ErrorEntity? = null,
    val data: T?,
) {
    companion object {

        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data = data)
        }

        fun <T> error(errorType: ErrorEntity?): Result<T> {
            return Result(Status.ERROR, errorType, null)
        }
    }
}
