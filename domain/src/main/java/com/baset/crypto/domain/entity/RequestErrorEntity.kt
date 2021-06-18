package com.baset.crypto.domain.entity

import com.baset.crypto.domain.base.BaseResponse

data class RequestErrorEntity(
    override val status: StatusEntity
) : BaseResponse
