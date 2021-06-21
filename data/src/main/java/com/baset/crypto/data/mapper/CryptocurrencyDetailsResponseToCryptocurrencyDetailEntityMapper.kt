package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.CryptocurrencyDetailResponse
import com.baset.crypto.data.entity.CryptocurrencyDetailsResponse
import com.baset.crypto.data.entity.StatusResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.ErrorCode
import javax.inject.Inject

class CryptocurrencyDetailsResponseToCryptocurrencyDetailEntityMapper @Inject constructor() :
    EntityMapper<CryptocurrencyDetailsResponse, CryptocurrencyDetailEntity> {
    override fun mapToEntity(entity: CryptocurrencyDetailEntity): CryptocurrencyDetailsResponse {
        return CryptocurrencyDetailsResponse(
            StatusResponse("", ErrorCode.NO_ERROR.numberValue, null, 0, 0),
            CryptocurrencyDetailResponse(
                entity.id,
                entity.name,
                entity.symbol,
                entity.category,
                entity.description,
                entity.slug,
                entity.logo,
                entity.subreddit,
                entity.notice,
                entity.dateAdded,
                entity.twitterUsername
            )
        )
    }

    override fun mapFromEntity(entity: CryptocurrencyDetailsResponse): CryptocurrencyDetailEntity {
        return CryptocurrencyDetailEntity(
            entity.data.id,
            entity.data.name,
            entity.data.symbol,
            entity.data.category,
            entity.data.description,
            entity.data.slug,
            entity.data.logo,
            entity.data.subreddit,
            entity.data.notice,
            entity.data.dateAdded,
            entity.data.twitterUsername
        )
    }

}