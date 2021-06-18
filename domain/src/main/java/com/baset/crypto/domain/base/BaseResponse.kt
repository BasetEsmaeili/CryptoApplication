package com.baset.crypto.domain.base

import com.baset.crypto.domain.entity.StatusEntity

/**
 * a Contract for get Responses from Remote
 */

interface BaseResponse {
    val status: StatusEntity
}