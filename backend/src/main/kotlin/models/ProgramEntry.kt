package models

import kotlinx.serialization.Serializable

@Serializable
data class ProgramEntry(
    val name: String,
    val id: String,
    val description: String,
    val tags: List<Category>,
    val timeRange: TimeRange,
    val roomId: String,
    val speakers: List<SpeakerRef>,
    val isCanceled: Boolean,
    val format: Format,
)