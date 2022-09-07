package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oradore.api.NetworkApi
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
import kotlinx.coroutines.launch

class AppViewModel(
    private val savedState: SavedStateHandle,
) : ViewModel() {

    lateinit var api: NetworkApi

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<ProgramEntry>())
        private set

    private var favoriteProgramEntries by mutableStateOf(setOf<String>())

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    var speakers by mutableStateOf(emptyList<Speaker>())
        private set

    fun fetchProgramEntries() {
        viewModelScope.launch {
            programEntries = api.fetchProgramEntries()
        }
    }

    fun programEntryById(id: String) =
        programEntries.firstOrNull { it.id == id }

    fun roomById(id: String) =
        rooms.find { it.id == id }

    fun isFavorite(programEntryId: String) =
        favoriteProgramEntries.contains(programEntryId)

    private fun fav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries + programEntryId
    }

    private fun unFav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries - programEntryId
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

    fun favorites(): List<ProgramEntry> =
        programEntries.filter { isFavorite(it.id) }
}