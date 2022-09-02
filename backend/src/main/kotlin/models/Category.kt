package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category(val abbrev: String) {
    @SerialName("Data Driven")
    DATA_DRIVEN("rwm"),

    @SerialName("Privacy + Datenschutz")
    PRIVACY("ygj"),

    @SerialName("Digital Factory")
    DIGITAL_FACTORY("cxh"),

    @SerialName("Digitalisierung im Public Sector")
    DIGITALIZATION("euf"),

    @SerialName("IT Driven")
    IT_DRIVEN("xoo"),

    @SerialName("Digitale Produkte + Geschäftsmodelle")
    DIGITAL_PRODUCTS("irf"),

    @SerialName("Auf dem Weg zur agilen Organisation")
    AGILE_ORGANIZATION("tlj"),

    @SerialName("Digitale Kunden- und Marktzentrierung")
    CUSTOMER_MARKET_CENTRALIZATION("uww"),

    @SerialName("")
    OTHER("");

    companion object {
        fun fromAbbrev(abbrev: String) = when (abbrev) {
            "rwm" -> DATA_DRIVEN
            "ygj" -> PRIVACY
            "cxh" -> DIGITAL_FACTORY
            "euf" -> DIGITALIZATION
            "xoo" -> IT_DRIVEN
            "irf" -> DIGITAL_PRODUCTS
            "tlj" -> AGILE_ORGANIZATION
            "uww" -> CUSTOMER_MARKET_CENTRALIZATION
            else -> OTHER
        }
    }
}