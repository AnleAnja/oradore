package com.example.oradore.models

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: String,
    val name: String,
    val desc: String,
    val roomLocation: RoomLocation?
)