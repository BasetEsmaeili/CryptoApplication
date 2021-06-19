package com.baset.crypto.domain.entity

enum class ErrorCode(val numberValue: Int) {
    NO_ERROR(0),
    UNAUTHORIZED(401),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    TO_MANY_REQUESTS(429),
    INTERNAL_SERVER_ERROR(500),
    UNKNOWN_ERROR(-1)
}