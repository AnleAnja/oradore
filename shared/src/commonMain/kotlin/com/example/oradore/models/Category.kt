package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category(val abbrev: String, val label: String) {
    @SerialName("Data Driven")
    DATA_DRIVEN("rwm", "Data Driven"),

    @SerialName("Privacy + Datenschutz")
    PRIVACY("ygj", "Privacy + Datenschutz"),

    @SerialName("Digital Factory")
    DIGITAL_FACTORY("cxh", "Digital Factory"),

    @SerialName("Digitalisierung im Public Sector")
    DIGITALIZATION("euf", "Digitalisierung im Public Sector"),

    @SerialName("IT Driven")
    IT_DRIVEN("xoo", "IT Driven"),

    @SerialName("Digitale Produkte + Geschäftsmodelle")
    DIGITAL_PRODUCTS("irf", "Digitale Produkte + Geschäftsmodelle"),

    @SerialName("Auf dem Weg zur agilen Organisation")
    AGILE_ORGANIZATION("tlj", "Auf dem Weg zur agilen Organisation"),

    @SerialName("Digitale Kunden- und Marktzentrierung")
    CUSTOMER_MARKET_CENTRALIZATION("uww", "Digitale Kunden- und Marktzentrierung"),

    @SerialName("")
    OTHER("", "");

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