package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    @SerialName("roomId")
    val id: String,
    val name: String
)