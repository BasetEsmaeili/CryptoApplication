package com.baset.crypto.domain.entity.params

data class GetRemoteCryptocurrenciesParams(
    val page: Int = 1,
    val pageLimit: Int = 20
)