package com.baset.crypto.domain.entity

sealed class ErrorEntity {
    sealed class ApiError : ErrorEntity() {
        object Timeout : ApiError()
        object NoNetwork : ApiError()
        object UnAuthorized : ApiError()
        object BadRequest : ApiError()
        object Forbidden : ApiError()
        object ToManyRequests : ApiError()
        object InternalServerError : ApiError()
        data class Unknown(val message: String?, val statusCode: Int) : ApiError()
    }
}
