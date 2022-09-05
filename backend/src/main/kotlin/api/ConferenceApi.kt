package api

import com.example.oradore.models.*
import database.ProgramEntryDao
import database.RoomDao
import database.SpeakerDao
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
private data class ProgramEntryJson(
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
private data class ProgramResult(val lectures: List<ProgramEntryJson>)

@Serializable
private data class SpeakerResult(val model: Speaker)

@Serializable
private data class SpeakerApiResult(val speakerById: Map<String, SpeakerResult>)

@Serializable
data class RoomJson(
    @SerialName("roomId")
    val id: String,
    val name: String
)

@Serializable
private data class RoomResult(val model: RoomJson)

@Serializable
private data class RoomApiResult(val roomById: Map<String, RoomResult>)

@Serializable
private data class DetailPostBody(
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
private data class ProgramPostBody(
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

class ConferenceApi(
    private val baseUrl: String,
    private val programEntryDao: ProgramEntryDao,
    private val roomDao: RoomDao,
    private val speakerDao: SpeakerDao
) {

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

    suspend fun fetchAndSaveProgram() {
        val programEntries = fetchProgramEntries()
        val roomIds = programEntries
            .map { it.roomId }
            .distinct()
        val speakerIds = programEntries
            .flatMap { speakerRef -> speakerRef.speakers.map { it.id } }
            .distinct()
        val allSpeakers = fetchSpeakers(speakerIds)
        val allRooms = fetchRooms(roomIds)
        roomDao.insertRooms(allRooms)
        speakerDao.insertSpeakers(allSpeakers)
        programEntryDao.insertProgramEntries(programEntries)
    }

    private suspend fun fetchSpeakers(speakerIds: List<String>): List<Speaker> =
        http.post("$baseUrl/read") {
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
            .speakerById.map { it.value.model }

    private suspend fun fetchRooms(roomIds: List<String>): List<Room> =
        http.post("$baseUrl/read") {
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
            .roomById
            .map { toRoom(it.value.model) }

    private suspend fun fetchProgramEntries(): List<ProgramEntry> =
        http.post("$baseUrl/list/lectures") {
            contentType(ContentType.Application.Json)
            setBody(programPostBody)
        }.body<ProgramResult>()
            .lectures
            .map(::toProgramEntry)

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

    private fun toRoom(json: RoomJson): Room {
        fun nameAndDescription(): Pair<String, String> {
            val split = json.name.split("|", limit = 2)
            return when (split.size) {
                0 -> Pair("", "")
                1 -> Pair(split[0].trim(), "")
                else -> Pair(split[0].trim(), split[1].trim())
            }
        }

        val (name, description) = nameAndDescription()

        return Room(
            json.id,
            name,
            description,
            RoomLocation.fromRoomId(json.id)
        )
    }
}