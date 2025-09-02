package com.team.wordapp.data.model

import java.time.ZonedDateTime
import java.time.ZoneId

data class Word (
    val id: Int? = null,
    val title: String,
    val meaning: String,
    val synonyms: String? = null,
    val details: String? = null,
    val createdAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kuala_Lumpur")),
    val isCompleted: Boolean = false
)
