package com.example.oradore.api

import com.example.oradore.models.ProgramEntry
import kotlinx.serialization.Serializable

@Serializable
data class GroupProgramEntries(val start: Long, val entries: List<ProgramEntry>)