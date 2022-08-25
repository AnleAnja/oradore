package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonArray

@Serializable
data class ProgramEntry(
    val name: String,
    @SerialName("lectureId")
    val id: String,
    val description: String,
    val tags: List<Category>,
    @SerialName("dateTimeRange")
    val timeRange: TimeRange,
    val roomId: String,
    val speakers: List<String>,
    val speakerRole: Map<String, String>,
    val isCanceled: Boolean,
    val format: Format,
    )

@Serializable
data class ProgramResult(val lectures: List<ProgramEntry>)

/*

  "sessionId": "gZSB6JvvfZ1Gevw0zEon",
  "locale": "DE_DE",
  "orgId": "WApCpx7b6ApA9cmlgY8y",
  "speakerId": null,
  "vendorId": null,
  "tags": [],
  "title": "",
  "dateTimeRange": null,
  "cursor": "Ck8KGQoMcGVyaW9kX3N0YXJ0EgkIgJDhtuyb-gISLmoLZX50YWxxdWUtNDJyHwsSBUV2ZW50IhRFczVYdFNpRTJsdVkxSkM2M2UwRwwYACAB",
  "limit": 100
 */

@Serializable
data class PostBody(
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

data class Speaker(val id: String, val role: Role)

enum class Role(val label: String) {
    SPEAKER("Speaker"),
    MODERATOR("Moderation")
}

@Serializable
data class TimeRange (val start: Long, val end: Long)

@Serializable
enum class Format(val label: String) {
    @SerialName("wxp")
    LIGHTNING_TALK("Kurzvortrag"),
    @SerialName("snh")
    TALK("Vortrag"),
    @SerialName("vjv")
    WORKSHOP("Workshop"),
    @SerialName("qdn")
    DISCUSSION("Podiumsdiskussion"),
    @SerialName("ctj")
    TOUR("Führung"),
    @SerialName("nci")
    KEYNOTE("Keynote");

    override fun toString(): String {
        return label
    }
}

@Serializable
enum class Category(val label: String) {
    @SerialName("rwm")
    DATA_DRIVEN("Data Driven"),
    @SerialName("ygj")
    PRIVACY("Privacy + Datenschutz"),
    @SerialName("cxh")
    DIGITAL_FACTORY("Digital Factory"),
    @SerialName("euf")
    DIGITALIZATION("Digitalisierung im Public Sector"),
    @SerialName("xoo")
    IT_DRIVEN("IT Driven"),
    @SerialName("irf")
    DIGITAL_PRODUCTS("Digitale Produkte + Geschäftsmodelle"),
    @SerialName("tlj")
    AGILE_ORGANIZATION("Auf dem Weg zur agilen Organisation"),
    @SerialName("uww")
    CUSTOMER_MARKET_CENTRALIZATION("Digitale Kunden- und Marktzentrierung")
}

class ConferenceApi(val url: String) {

    private val http = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchProgram(): ProgramResult {
        return http.post(url + "/list/lectures") {
            contentType(ContentType.Application.Json)
            setBody(PostBody(
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
            ))
        }.body<ProgramResult>()
    }
}