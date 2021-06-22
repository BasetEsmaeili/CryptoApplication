package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.db.CryptocurrencyDetailEntity
import com.baset.crypto.domain.base.EntityMapper
import javax.inject.Inject

class CryptocurrencyDetailEntityToDbCryptocurrencyEntityMapper @Inject constructor() :
    EntityMapper<CryptocurrencyDetailEntity?, com.baset.crypto.domain.entity.CryptocurrencyDetailEntity?> {
    override fun mapToEntity(entity: com.baset.crypto.domain.entity.CryptocurrencyDetailEntity?): CryptocurrencyDetailEntity? {
        return if (entity != null) CryptocurrencyDetailEntity(
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
        else null
    }

    override fun mapFromEntity(entity: CryptocurrencyDetailEntity?): com.baset.crypto.domain.entity.CryptocurrencyDetailEntity? {
        return if (entity != null) com.baset.crypto.domain.entity.CryptocurrencyDetailEntity(
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
        else null
    }
}