package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Role(val label: String) {
    @SerialName("speaker")
    SPEAKER("Speaker"),

    @SerialName("moderation")
    MODERATOR("Moderation"),

    OTHER("Sonstige");

    companion object {
        fun fromAbbrev(abbrev: String) = when (abbrev) {
            "Speaker" -> Role.SPEAKER
            "Moderation" -> Role.MODERATOR
            else -> Role.OTHER
        }
    }
}