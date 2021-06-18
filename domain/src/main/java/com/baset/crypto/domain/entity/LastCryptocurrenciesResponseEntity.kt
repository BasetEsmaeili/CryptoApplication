package com.baset.crypto.domain.entity

import com.baset.crypto.domain.base.BaseResponse

data class LastCryptocurrenciesResponseEntity(
    override val status: StatusEntity,
    val data: List<CryptocurrencyEntity>
) : BaseResponse
