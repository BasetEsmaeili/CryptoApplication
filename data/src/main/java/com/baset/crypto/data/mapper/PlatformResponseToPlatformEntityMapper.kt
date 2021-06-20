package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.PlatformResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.PlatformEntity
import javax.inject.Inject

class PlatformResponseToPlatformEntityMapper @Inject constructor() :
    EntityMapper<PlatformResponse?, PlatformEntity?> {
    override fun mapToEntity(entity: PlatformEntity?): PlatformResponse? {
        return if (entity != null)
            PlatformResponse(
                entity.id,
                entity.name,
                entity.symbol,
                entity.slug,
                entity.tokenAddress
            )
        else null
    }

    override fun mapFromEntity(entity: PlatformResponse?): PlatformEntity? {
        return if (entity != null)
            PlatformEntity(
                entity.id,
                entity.name,
                entity.symbol,
                entity.slug,
                entity.tokenAddress
            )
        else null
    }

}