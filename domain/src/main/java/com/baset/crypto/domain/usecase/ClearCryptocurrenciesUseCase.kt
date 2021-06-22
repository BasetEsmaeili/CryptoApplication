package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.base.BaseUseCase
import com.baset.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

class ClearCryptocurrenciesUseCase @Inject constructor(private val repository: CryptoRepository) :
    BaseUseCase<Unit, Nothing?>() {
    override suspend fun create(params: Nothing?) {
        repository.clearCryptocurrencies()
    }
}