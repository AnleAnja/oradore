package com.example.oradore.models

import com.example.oradore.models.*
import kotlinx.serialization.Serializable

@Serializable
data class ProgramEntryPreview(
    val name: String,
    val id: String,
    val tags: Category,
    val timeRange: TimeRange,
    val room: Room,
    val speakers: List<SpeakerPreview>,
    val isCanceled: Boolean,
    val format: Format
)