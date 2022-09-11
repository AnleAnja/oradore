package com.example.oradore.viewmodel

import com.example.oradore.api.GroupProgramEntries
import com.example.oradore.models.Room
import com.example.oradore.models.Speaker

interface ViewModelObserver {
    fun updateProgramEntries(entries: List<GroupProgramEntries>)
    fun updateRooms(rooms: List<Room>)
    fun updateSpeakers(speakers: List<Speaker>)
    fun updateFavorites(favorites: List<GroupProgramEntries>)
}