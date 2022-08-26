package api

import database.ProgramEntryDao
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProgramEntryJson(
    val name: String,
    @SerialName("lectureId")
    val id: String,
    val description: String,
    val tags: List<String>,
    @SerialName("dateTimeRange")
    val timeRange: TimeRange,
    val roomId: String,
    val speakers: List<String>,
    val speakerRole: Map<String, String>,
    val isCanceled: Boolean,
    val format: String,
)

@Serializable
data class ProgramEntry(
    val name: String,
    val id: String,
    val description: String,
    val tags: List<Category>,
    val timeRange: TimeRange,
    val roomId: String,
    val speakers: List<SpeakerRef>,
    val isCanceled: Boolean,
    val format: Format,
)

@Serializable
data class ProgramResult(val lectures: List<ProgramEntryJson>)

@Serializable
data class DetailPostBody(
    val sessionId: String,
    val locale: String,
    val config: Boolean,
    val viewId: String?,
    val agencyIdList: List<String>,
    val orgIdList: List<String>,
    val orgId: String,
    val roomIdList: List<String>,
    val speakerIdList: List<String>,
    val lectureIdList: List<String>,
    val vendorIdList: List<String>,
    val catalogIdList: List<String>,
    val productIdList: List<String>,
    val pageIdList: List<String>,
    val analyticsList: List<String>
)

@Serializable
data class ProgramPostBody(
    val sessionId: String,
    val locale: String,
    val orgId: String,
    val speakerId: String?,
    val vendorId: String?,
    val tags: List<String>,
    val title: String,
    val dateTimeRange: String?,
    val cursor: String,
    val limit: Int,
)

@Serializable
data class SpeakerRef(val id: String, val role: Role)

@Serializable
enum class Role(val label: String) {
    @SerialName("speaker")
    SPEAKER("Speaker"),

    @SerialName("moderation")
    MODERATOR("Moderation")
}

@Serializable
data class TimeRange(val start: Long, val end: Long)

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

@Serializable
data class SpeakerApiResult(val speakerById: Map<String, Map<String, Speaker>>)

@Serializable
data class RoomApiResult(val roomById: Map<String, Map<String, Room>>)

class ConferenceApi(val url: String) {

    private val http = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val programPostBody = ProgramPostBody(
        "gZSB6JvvfZ1Gevw0zEon",
        "DE_DE",
        "WApCpx7b6ApA9cmlgY8y",
        null,
        null,
        emptyList(),
        "",
        null,
        "Ck8KGQoMcGVyaW9kX3N0YXJ0EgkIgJDhtuyb-gISLmoLZX50YWxxdWUtNDJyHwsSBUV2ZW50IhRFczVYdFNpRTJsdVkxSkM2M2UwRwwYACAB",
        100
    )

    suspend fun fetchProgram() {
        val programEntries = fetchProgramEntries()
        val roomIds = programEntries.map {
            it.roomId
        }.distinct()
        val speakerIds = programEntries.flatMap { speakerRef ->
            speakerRef.speakers.map {
                it.id
            }
        }.distinct()
        val allSpeakers = fetchSpeakers(speakerIds)
        val allRooms = fetchRooms(roomIds)

        val program = programEntries.map { programEntry ->
            val room = allRooms.find { it.id == programEntry.roomId } !!
            val speakers = allSpeakers.filter { s ->
                programEntry.speakers.any {
                    it.id == s.id
                }
            }
            Triple(programEntry, room, speakers)
        }
        val dao = ProgramEntryDao()
        program.forEach { (programEntry, room, speakers) ->
            dao.insert(room, speakers, programEntry)
        }
    }

    suspend fun fetchSpeakers(speakerIds: List<String>): List<Speaker> {
        return http.post(url + "/read") {
            contentType(ContentType.Application.Json)
            setBody(
                DetailPostBody(
                    "gZSB6JvvfZ1Gevw0zEon",
                    "DE_DE",
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    "WApCpx7b6ApA9cmlgY8y",
                    emptyList(),
                    speakerIds,
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList()
                )
            )
        }.body<SpeakerApiResult>()
            .speakerById.flatMap { speakers ->
                speakers.value.map {
                    it.value
                }
            }
    }

    suspend fun fetchRooms(roomIds: List<String>): List<Room> {
        return http.post(url + "/read") {
            contentType(ContentType.Application.Json)
            setBody(
                DetailPostBody(
                    "gZSB6JvvfZ1Gevw0zEon",
                    "DE_DE",
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    "WApCpx7b6ApA9cmlgY8y",
                    roomIds,
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList()
                )
            )
        }.body<RoomApiResult>()
            .roomById.flatMap { rooms ->
                rooms.value.map {
                    it.value
                }
            }
    }

    suspend fun fetchProgramEntries(): List<ProgramEntry> {
        return http.post(url + "/list/lectures") {
            contentType(ContentType.Application.Json)
            setBody(programPostBody)
        }.body<ProgramResult>()
            .lectures
            .map(::toProgramEntry)
    }

    private fun toProgramEntry(entry: ProgramEntryJson) =
        ProgramEntry(
            entry.name,
            entry.id,
            entry.description,
            entry.tags.map { Category.fromAbbrev(it) },
            entry.timeRange,
            entry.roomId,
            entry.speakers.map {
                SpeakerRef(
                    it,
                    Role.SPEAKER
                )
            } + entry.speakerRole.map { SpeakerRef(it.key, Role.MODERATOR) },
            entry.isCanceled,
            Format.fromAbbrev(entry.format)
        )
}