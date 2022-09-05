package com.example.oradore.models

import kotlinx.serialization.Serializable

@Serializable
data class RoomLocation(
    val buildingInfo: String,
    val url: String
) {
    companion object {
        private const val roomUrlPrefix = "http://www.gm.fh-koeln.de/~dobrynin/kmm/"

        const val Mainbuilding3 = "Hauptgebäude Stockwerk 3"
        const val Mensabuilding1 = "Mensagebäude Stockwerk 1"
        const val Mensabuilding0 = "Mensagebäude Erdgeschoss"

        fun fromRoomId(id: String): RoomLocation? =
            when (id) {
                "sF1w3rpkux6tnJbLDxJd" -> RoomLocation(Mainbuilding3, "3112")
                "AEH7Xya3DNS9kUE5yMyz" -> RoomLocation(Mainbuilding3, "3111")
                "Vyp1WaS98rfaTdvplF7V" -> RoomLocation(Mainbuilding3, "3107")
                "ka2ITleGSa6l92ZjzjNR" -> RoomLocation(Mainbuilding3, "3106")
                "sLbSh2c8DP9rPUXe0K2r" -> RoomLocation(Mainbuilding3, "3104")
                "8HpEkr7FJ6aXMhyChcuo" -> RoomLocation(Mainbuilding3, "3103")
                "q5ziDJLrl5LRgDTKqx0a" -> RoomLocation(Mainbuilding3, "3102")
                "lMb3NrdzXGn1iPxE4rB4" -> RoomLocation(Mainbuilding3, "3101")
                "db0VB7nhASaI0NJmE6hn" -> RoomLocation(Mainbuilding3, "3100")
                "LMvxs4ZZyhfTSLAeUeqU" -> RoomLocation(Mensabuilding1, "1400")
                "CeBYz8RIAw27ti3FOj5w" -> RoomLocation(Mensabuilding0, "0405")
                "Jj7lxXz7Jw8CKS1vnSVf" -> RoomLocation(Mensabuilding0, "0401")
                else -> null
            }?.let { it.copy(url = roomUrlPrefix + it) }
    }
}