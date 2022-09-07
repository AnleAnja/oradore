package com.example.oradore.api

import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
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

    @Throws(Exception::class)
    suspend fun fetchProgramEntries(): List<GroupProgramEntries> =
        http.get("$baseUrl/program").body<List<ProgramEntry>>()
            .groupBy { it.timeRange.start }
            .map { (key, value) -> GroupProgramEntries(key, value.sortedBy { it.timeRange.start }) }
            .sortedBy { it.start }

    @Throws(Exception::class)
    suspend fun fetchSpeakers(): List<Speaker> =
        http.get("$baseUrl/speakers").body<List<Speaker>>()
            .sortedBy { it.firstName }

    @Throws(Exception::class)
    suspend fun fetchRooms(): List<Room> =
        http.get("$baseUrl/rooms").body<List<Room>>()
            .sortedBy { it.name }
}