package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.oradore.api.DummyData
import com.example.oradore.models.ProgramEntryPreview
import com.example.oradore.models.Room

class AppViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<ProgramEntryPreview>())
        private set

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    fun fetchProgramEntries() {
        programEntries = DummyData.ProgramEntriesPreview()
    }

    fun fetchRooms() {
        rooms = DummyData.ProgramEntriesPreview().map { it.room }
    }
}