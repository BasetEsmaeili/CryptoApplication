package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.CryptocurrenciesResponse
import com.baset.crypto.data.entity.StatusResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.ErrorCode
import javax.inject.Inject

class CryptocurrenciesResponseToCryptocurrencyEntityListMapper @Inject constructor(
    private val cryptocurrencyMapper: CryptocurrencyResponseToCryptocurrencyEntityMapper
) : EntityMapper<CryptocurrenciesResponse?, List<CryptocurrencyEntity?>> {
    override fun mapToEntity(entity: List<CryptocurrencyEntity?>): CryptocurrenciesResponse {
        return CryptocurrenciesResponse(
            status = StatusResponse("", ErrorCode.NO_ERROR.numberValue, null, 0, 0),
            data = entity.filterNotNull().map { cryptocurrencyMapper.mapToEntity(it) }
        )
    }

    override fun mapFromEntity(entity: CryptocurrenciesResponse?): List<CryptocurrencyEntity?> {
        return entity?.data?.map { cryptocurrencyMapper.mapFromEntity(it) } ?: arrayListOf()
    }


}