package com.example.oradore.models

import kotlinx.serialization.Serializable

@Serializable
data class TimeRange(val start: Long, val end: Long)