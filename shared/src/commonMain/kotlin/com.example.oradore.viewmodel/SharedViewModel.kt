package com.example.oradore.viewmodel

import com.example.oradore.api.GroupProgramEntries
import com.example.oradore.api.NetworkApi
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker
import com.example.oradore.storage.FavoritesStorage
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(
    private val api: NetworkApi,
    private val storage: FavoritesStorage
) {

    @NativeCoroutineScope
    private val scope = CoroutineScope(Dispatchers.Default)

    private var programEntries: List<GroupProgramEntries> = emptyList()

    private var favoriteProgramEntries: Set<String> = setOf()

    private var rooms: List<Room> = emptyList()

    private var speakers: List<Speaker> = emptyList()

    private var observers: MutableList<ViewModelObserver> = mutableListOf()

    fun registerObserver(observer: ViewModelObserver) {
        observers += observer
    }

    fun unregisterObserver(observer: ViewModelObserver) {
        observers -= observer
    }

    fun fetchProgramEntries() {
        scope.launch {
            programEntries = api.fetchProgramEntries()
            observers.forEach { it.updateProgramEntries(programEntries) }
            favoriteProgramEntries = storage.getFavorites().toSet()
            observers.forEach { it.updateFavorites(favorites()) }
        }
    }

    fun fetchRooms() {
        scope.launch {
            rooms = api.fetchRooms()
            observers.forEach { it.updateRooms(rooms) }
        }
    }

    fun fetchSpeakers() {
        scope.launch {
            speakers = api.fetchSpeakers()
            observers.forEach { it.updateSpeakers(speakers) }
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

    fun speakerById(id: String) =
        speakers.find { it.id == id }

    fun isFavorite(programEntryId: String) =
        favoriteProgramEntries.contains(programEntryId)

    fun toggleFav(programEntryId: String) {
        if (isFavorite(programEntryId))
            unFav(programEntryId)
        else
            fav(programEntryId)

        storage.saveFavorites(favoriteProgramEntries.toList())
        observers.forEach { it.updateFavorites(favorites()) }
    }

    private fun fav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries + programEntryId
    }

    private fun unFav(programEntryId: String) {
        favoriteProgramEntries = favoriteProgramEntries - programEntryId
    }

    private fun favorites(): List<GroupProgramEntries> =
        filterProgramEntries { isFavorite(it.id) }

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
}