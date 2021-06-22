package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.base.BaseFlowUseCase
import com.baset.crypto.domain.entity.CryptocurrencyDetailEntity
import com.baset.crypto.domain.entity.params.CryptocurrencyDetailParams
import com.baset.crypto.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCryptocurrencyDetailUseCase @Inject constructor(private val repository: CryptoRepository) :
    BaseFlowUseCase<CryptocurrencyDetailEntity?, CryptocurrencyDetailParams>() {
    override fun createFlow(params: CryptocurrencyDetailParams?): Flow<CryptocurrencyDetailEntity?> {
        if (params == null) throw IllegalArgumentException("CryptocurrencyDetailParams must not be null")
        return repository.getLocalCryptocurrencyDetail(params.id)
    }
}