package com.team.wordapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime
import java.time.ZoneId

@Parcelize // Enables the data to be passed through components, fragments or activities
data class Word (
    val id: Int? = null,
    val title: String,
    val meaning: String,
    val synonyms: String? = null,
    val details: String? = null,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    val isCompleted: Boolean = false
) : Parcelable
