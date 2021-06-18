package com.baset.crypto.domain.entity

import com.baset.crypto.domain.base.BaseResponse

data class CryptocurrencyDetailResponseEntity(
    override val status: StatusEntity,
    val data: CryptocurrencyIInfoEntity
) : BaseResponse
