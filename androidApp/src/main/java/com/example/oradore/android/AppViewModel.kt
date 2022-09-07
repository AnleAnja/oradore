package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.oradore.api.DummyData
import com.example.oradore.models.ProgramEntryPreview
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker

class AppViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<ProgramEntryPreview>())
        private set

    private var favoriteProgramEntries by mutableStateOf(setOf<String>())

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    var speakers by mutableStateOf(emptyList<Speaker>())
        private set

    fun fetchProgramEntries() {
        programEntries = DummyData.ProgramEntriesPreview()
    }

    fun programEntryById(id: String) =
        DummyData.ProgramEntries().firstOrNull { it.id == id }

    fun roomById(id: String) =
        rooms.find { it.id == id }

    fun speakerPreviewByProgramId(id: String) =
        DummyData.ProgramEntriesPreview().firstOrNull { it.id == id }?.speakers

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
        rooms = DummyData.Rooms()
    }

    fun fetchSpeakers() {
        speakers = DummyData.Speakers()
    }

    fun speakerById(id: String) =
        speakers.find { it.id == id }

    fun favorites(): List<ProgramEntryPreview> =
        programEntries.filter { isFavorite(it.id) }
}