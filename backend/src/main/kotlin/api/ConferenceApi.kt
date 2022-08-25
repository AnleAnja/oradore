package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonArray

@Serializable
data class ProgramEntry(val description: String, val name: String)

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