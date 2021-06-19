package com.baset.crypto.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(override val status: StatusResponse) : BaseResponse
