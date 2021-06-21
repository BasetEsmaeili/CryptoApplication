package com.baset.crypto.data.entity

data class CryptocurrencyDetailsResponse(
    override val status: StatusResponse,
    val data: CryptocurrencyDetailResponse
) : BaseResponse