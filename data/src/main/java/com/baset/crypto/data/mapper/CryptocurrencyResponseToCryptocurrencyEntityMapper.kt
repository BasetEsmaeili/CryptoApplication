package com.baset.crypto.data.mapper

import com.baset.crypto.data.entity.CryptocurrencyResponse
import com.baset.crypto.domain.base.EntityMapper
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import javax.inject.Inject

class CryptocurrencyResponseToCryptocurrencyEntityMapper @Inject constructor(
    private val quoteMapper: QuoteResponseToQuoteEntityMapper,
    private val platformMapper: PlatformResponseToPlatformEntityMapper
) : EntityMapper<CryptocurrencyResponse, CryptocurrencyEntity> {
    override fun mapToEntity(entity: CryptocurrencyEntity): CryptocurrencyResponse {
        return CryptocurrencyResponse(
            entity.id,
            entity.name,
            entity.symbol,
            entity.slug,
            entity.numberOfMarketPairs,
            entity.dateAdded,
            entity.tags,
            entity.maxSupply,
            entity.circulatingSupply,
            entity.totalSupply,
            platformMapper.mapToEntity(entity.platform),
            entity.coinMarketRank,
            entity.lastUpdated,
            quoteMapper.mapToEntity(entity.quote)
        )
    }

    override fun mapFromEntity(entity: CryptocurrencyResponse): CryptocurrencyEntity {
        return CryptocurrencyEntity(
            entity.id,
            entity.name,
            entity.symbol,
            entity.slug,
            entity.numberOfMarketPairs,
            entity.dateAdded,
            entity.tags,
            entity.maxSupply,
            entity.circulatingSupply,
            entity.totalSupply,
            platformMapper.mapFromEntity(entity.platform),
            entity.coinMarketRank,
            entity.lastUpdated,
            quoteMapper.mapFromEntity(entity.quote)
        )
    }
}