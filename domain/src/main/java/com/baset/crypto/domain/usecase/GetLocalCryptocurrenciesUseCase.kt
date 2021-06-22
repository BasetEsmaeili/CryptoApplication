package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.base.BaseFlowUseCase
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCryptocurrenciesUseCase @Inject constructor(private val repository: CryptoRepository) :
    BaseFlowUseCase<List<CryptocurrencyEntity?>?,Nothing?>() {
    override fun createFlow(params: Nothing?): Flow<List<CryptocurrencyEntity?>?> {
        return repository.getCryptocurrencies()
    }
}