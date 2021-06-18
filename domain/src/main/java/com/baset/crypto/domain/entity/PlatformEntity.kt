package com.baset.crypto.domain.entity

data class PlatformEntity(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val tokenAddress: String
)