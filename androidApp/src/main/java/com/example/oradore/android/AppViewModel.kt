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
import com.example.oradore.models.SpeakerPreview

class AppViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<ProgramEntryPreview>())
        private set

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    var speakers by mutableStateOf(emptyList<Speaker>())
        private set

    fun fetchProgramEntries() {
        programEntries = DummyData.ProgramEntriesPreview()
    }

    fun fetchRooms() {
        rooms = DummyData.Rooms()
    }

    fun fetchSpeakers() {
        speakers = DummyData.Speakers()
    }

}