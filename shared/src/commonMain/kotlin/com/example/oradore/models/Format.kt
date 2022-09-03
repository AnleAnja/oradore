package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Format(val abbrev: String, val label: String, val hexColor: String) {
    @SerialName("Kurzvortrag")
    LIGHTNING_TALK("wxp", "Kurzvortrag", "#009fe3"),

    @SerialName("Vortrag")
    TALK("snh", "Vortrag", "#2d52a0"),

    @SerialName("Workshop")
    WORKSHOP("vjv", "Workshop", "#0d0a2b"),

    @SerialName("Podiumsdiskussion")
    DISCUSSION("qdn", "Podiumsdiskussion", "#009fe3"),

    @SerialName("Führung")
    TOUR("ctj", "Führung", "#581744"),

    @SerialName("Keynote")
    KEYNOTE("nci", "Keynote", "#b43092"),

    @SerialName("")
    OTHER("other", "Unbekannt", "#0d0a2b");

    companion object {
        fun fromAbbrev(abbrev: String) = when (abbrev) {
            "wxp" -> LIGHTNING_TALK
            "snh" -> TALK
            "vjv" -> WORKSHOP
            "qdn" -> DISCUSSION
            "ctj" -> TOUR
            "nci" -> KEYNOTE
            else -> OTHER
        }
    }

}