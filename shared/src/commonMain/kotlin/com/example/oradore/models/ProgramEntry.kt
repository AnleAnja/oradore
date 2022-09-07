package com.example.oradore.models

import kotlinx.serialization.Serializable

@Serializable
data class ProgramEntry(
    val name: String,
    val id: String,
    val description: String,
    val category: Category,
    val timeRange: TimeRange,
    val room: Room,
    val speakers: List<Pair<Speaker, Role>>,
    val isCanceled: Boolean,
    val format: Format,
)