package com.example.oradore.api

import com.example.oradore.models.ProgramEntryPreview
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NetworkApi(private val baseUrl: String) {
    private val http = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchProgramEntries(): List<ProgramEntryPreview> =
        http.get("$baseUrl/program").body()
}