package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.oradore.api.DummyData
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.ProgramEntryPreview
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
import com.example.oradore.models.SpeakerPreview

class AppViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<ProgramEntryPreview>())
        private set


    private var favoriteProgramEntries by mutableStateOf(setOf<ProgramEntry>())

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
        DummyData.ProgramEntriesPreview().firstOrNull { it.room.id == id }?.room

    fun speakerPreviewByProgramId(id: String) =
        DummyData.ProgramEntriesPreview().firstOrNull { it.id == id }?.speakers

    fun isFavorite(programEntry: ProgramEntry) =
        favoriteProgramEntries.contains(programEntry)

    private fun fav(programEntry: ProgramEntry) {
        favoriteProgramEntries = favoriteProgramEntries + programEntry
    }

    private fun unFav(programEntry: ProgramEntry) {
        favoriteProgramEntries = favoriteProgramEntries - programEntry
    }

    fun toggleFav(programEntry: ProgramEntry) {
        if (isFavorite(programEntry))
            unFav(programEntry)
        else
            fav(programEntry)
    }

    fun fetchRooms() {
        rooms = DummyData.Rooms()
    }

    fun fetchSpeakers() {
        speakers = DummyData.Speakers()
    }

}