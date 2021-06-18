package com.baset.crypto.domain.entity

data class UrlListEntity(
    val website: List<String>,
    val twitter: List<String>,
    val messageBoard: List<String>,
    val chat: List<String>,
    val explorer: List<String>,
    val reddit: List<String>,
    val technicalDoc: List<String>,
    val sourceCode: List<String>,
    val announcement: List<String>
)
