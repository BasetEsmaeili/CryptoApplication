package com.baset.crypto.domain.usecase

import com.baset.crypto.domain.base.BaseUseCase
import com.baset.crypto.domain.entity.CryptocurrencyEntity
import com.baset.crypto.domain.entity.Resource
import com.baset.crypto.domain.entity.params.GetRemoteCryptocurrenciesParams
import com.baset.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

class GetRemoteCryptocurrenciesUseCase @Inject constructor(private val repository: CryptoRepository) :
    BaseUseCase<Resource<List<CryptocurrencyEntity?>>, GetRemoteCryptocurrenciesParams>() {
    override suspend fun create(params: GetRemoteCryptocurrenciesParams?): Resource<List<CryptocurrencyEntity?>> {
        if (params == null) throw IllegalArgumentException("GetRemoteCryptocurrenciesParams must not be null")
        return repository.getCryptocurrencies(params.page, params.pageLimit)
    }
}