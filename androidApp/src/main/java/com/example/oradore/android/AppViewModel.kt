package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oradore.api.GroupProgramEntries
import com.example.oradore.api.NetworkApi
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
import com.example.oradore.storage.FavoritesStorage
import kotlinx.coroutines.launch

class AppViewModel() : ViewModel() {

    lateinit var api: NetworkApi
    lateinit var storage: FavoritesStorage

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<GroupProgramEntries>())
        private set

    private var favoriteProgramEntries by mutableStateOf(setOf<String>())

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    var speakers by mutableStateOf(emptyList<Speaker>())
        private set

    fun init() {
        favoriteProgramEntries = storage.getFavorites().toSet()
    }

    fun fetchProgramEntries() {
        viewModelScope.launch {
            programEntries = api.fetchProgramEntries()
        }
    }

    fun programEntryById(id: String): ProgramEntry? {
        programEntries.forEach { group ->
            group.entries.forEach { entry ->
                if (entry.id == id)
                    return entry
            }
        }
        return null
    }

    fun roomById(id: String) =
        rooms.find { it.id == id }

    fun isFavorite(programEntryId: String) =
        favoriteProgramEntries.contains(programEntryId)

    private fun fav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries + programEntryId
        storage.saveFavorites(favoriteProgramEntries.toList())
    }

    private fun unFav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries - programEntryId
        storage.saveFavorites(favoriteProgramEntries.toList())
    }

    fun toggleFav(programEntryId: String) {
        if (isFavorite(programEntryId))
            unFav(programEntryId)
        else
            fav(programEntryId)
    }

    fun fetchRooms() {
        viewModelScope.launch {
            rooms = api.fetchRooms()
        }
    }

    fun fetchSpeakers() {
        viewModelScope.launch {
            speakers = api.fetchSpeakers()
        }
    }

    fun speakerById(id: String) =
        speakers.find { it.id == id }

    fun filterProgramEntries(where: (ProgramEntry) -> Boolean): List<GroupProgramEntries> {
        val filtered = mutableListOf<GroupProgramEntries>()
        programEntries.forEach { group ->
            val newEntries = group.entries.filter(where)
            if (newEntries.isNotEmpty()) {
                filtered += group.copy(entries = newEntries)
            }
        }
        return filtered
    }

    fun favorites(): List<GroupProgramEntries> =
        filterProgramEntries { isFavorite(it.id) }
}