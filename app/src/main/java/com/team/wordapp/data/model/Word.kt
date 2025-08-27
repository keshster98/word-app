package com.team.wordapp.data.model

data class Word (
    val id: Int? = null,
    val title: String,
    val meaning: String,
    val synonym: String? = null,
    val details: String? = null
)
