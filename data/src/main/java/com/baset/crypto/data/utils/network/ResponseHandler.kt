package com.baset.crypto.data.utils.network

import com.baset.crypto.data.entity.BaseResponse
import com.baset.crypto.domain.entity.ErrorCode
import com.baset.crypto.data.entity.ErrorResponse
import com.baset.crypto.data.utils.errorLog
import com.baset.crypto.data.utils.exceptions.NoConnectionException
import com.baset.crypto.domain.entity.ErrorEntity
import com.baset.crypto.domain.entity.Result
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ResponseHandler @Inject constructor() {

    fun <T : BaseResponse> getErrorResult(throwable: Throwable): Result<T> {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    ErrorCode.UNAUTHORIZED.numberValue -> Result.error(ErrorEntity.ApiError.UnAuthorized)
                    ErrorCode.BAD_REQUEST.numberValue -> Result.error(ErrorEntity.ApiError.BadRequest)
                    ErrorCode.FORBIDDEN.numberValue -> Result.error(ErrorEntity.ApiError.Forbidden)
                    ErrorCode.TO_MANY_REQUESTS.numberValue -> Result.error(ErrorEntity.ApiError.ToManyRequests)
                    ErrorCode.INTERNAL_SERVER_ERROR.numberValue -> Result.error(ErrorEntity.ApiError.InternalServerError)
                    else -> {
                        val parsedData = parsErrorBody(throwable)
                        Result.error(
                            ErrorEntity.ApiError.Unknown(
                                parsedData?.status?.errorMessage,
                                parsedData?.status?.errorCode!!
                            )
                        )
                    }
                }
            }
            is NoConnectionException -> Result.error(ErrorEntity.ApiError.NoNetwork)
            is SocketTimeoutException -> Result.error(ErrorEntity.ApiError.Timeout)
            else -> {
                errorLog<ResponseHandler>(throwable)
                Result.error(
                    ErrorEntity.ApiError.Unknown(
                        throwable.message,
                        ErrorCode.UNKNOWN_ERROR.numberValue
                    )
                )
            }
        }
    }


    private fun parsErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let { buffered ->
                buffered.use { it.readString(Charsets.UTF_8) }
                    .toSerializableModel<ErrorResponse>()
            }
        } catch (exception: Exception) {
            errorLog<ResponseHandler>(throwable)
            null
        }
    }
}