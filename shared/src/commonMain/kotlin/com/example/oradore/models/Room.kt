package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: String,
    val name: String,
    val description: String,
    val url: String?
)