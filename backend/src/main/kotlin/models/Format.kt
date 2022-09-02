package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Format(val abbrev: String) {
    @SerialName("Kurzvortrag")
    LIGHTNING_TALK("wxp"),

    @SerialName("Vortrag")
    TALK("snh"),

    @SerialName("Workshop")
    WORKSHOP("vjv"),

    @SerialName("Podiumsdiskussion")
    DISCUSSION("qdn"),

    @SerialName("Führung")
    TOUR("ctj"),

    @SerialName("Keynote")
    KEYNOTE("nci"),

    @SerialName("")
    OTHER("");

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