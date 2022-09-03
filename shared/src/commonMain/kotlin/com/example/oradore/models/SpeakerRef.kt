package com.example.oradore.models

import kotlinx.serialization.Serializable

@Serializable
data class SpeakerRef(val id: String, val role: Role)