package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.oradore.api.GroupProgramEntries
import com.example.oradore.api.NetworkApi
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
import com.example.oradore.storage.FavoritesStorage
import com.example.oradore.viewmodel.SharedViewModel
import com.example.oradore.viewmodel.ViewModelObserver

class AppViewModel : ViewModel(), ViewModelObserver {

    var screen by mutableStateOf(0)

    var programEntries by mutableStateOf(emptyList<GroupProgramEntries>())
        private set

    var favorites by mutableStateOf(emptyList<GroupProgramEntries>())
        private set

    var rooms by mutableStateOf(emptyList<Room>())
        private set

    var speakers by mutableStateOf(emptyList<Speaker>())
        private set

    private lateinit var viewModel: SharedViewModel

    fun init(api: NetworkApi, storage: FavoritesStorage) {
        viewModel = SharedViewModel(api, storage)
        viewModel.registerObserver(this)
    }

    override fun onCleared() {
        super.onCleared()
        viewModel.unregisterObserver(this)
    }

    override fun updateProgramEntries(entries: List<GroupProgramEntries>) {
        this.programEntries = entries
    }

    override fun updateRooms(rooms: List<Room>) {
        this.rooms = rooms
    }

    override fun updateSpeakers(speakers: List<Speaker>) {
        this.speakers = speakers
    }

    override fun updateFavorites(favorites: List<GroupProgramEntries>) {
        this.favorites = favorites
    }

    fun fetchProgramEntries() {
        viewModel.fetchProgramEntries()
    }

    fun fetchRooms() {
        viewModel.fetchRooms()
    }

    fun fetchSpeakers() {
        viewModel.fetchSpeakers()
    }

    fun programEntryById(id: String): ProgramEntry? =
        viewModel.programEntryById(id)

    fun roomById(id: String) =
        viewModel.roomById(id)

    fun speakerById(id: String) =
        viewModel.speakerById(id)

    fun isFavorite(programEntryId: String) =
        favorites.any { it.entries.any { it.id == programEntryId } }

    fun toggleFav(programEntryId: String) {
        viewModel.toggleFav(programEntryId)
    }

    fun filterProgramEntries(where: (ProgramEntry) -> Boolean): List<GroupProgramEntries> =
        viewModel.filterProgramEntries(where)
}