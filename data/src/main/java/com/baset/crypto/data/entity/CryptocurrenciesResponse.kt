package com.baset.crypto.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CryptocurrenciesResponse(
    override val status: StatusResponse,
    val data: List<CryptocurrencyResponse>
) : BaseResponse