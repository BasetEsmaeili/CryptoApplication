package com.baset.crypto.domain.entity

data class StatusEntity(
    val timestamp: String,
    val errorCode: Int,
    val errorMessage: String? = null,
    val elapsed: Long,
    val creditCount: Long
)