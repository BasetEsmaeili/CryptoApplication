package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.base.BaseUseCase
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.Result
import com.baset.crypto.domain.entity.params.CryptocurrencyDetailParams
import com.baset.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

class GetRemoteCryptocurrencyDetailUseCase @Inject constructor(private val repository: CryptoRepository):BaseUseCase<Result<CryptocurrencyDetailEntity>,CryptocurrencyDetailParams>(){
    override suspend fun create(params: CryptocurrencyDetailParams?): Result<CryptocurrencyDetailEntity> {
        if (params == null) throw IllegalArgumentException("Cryptocurrency Id must not be null")
        return repository.getRemoteCryptocurrencyDetail(params.id)
    }
}