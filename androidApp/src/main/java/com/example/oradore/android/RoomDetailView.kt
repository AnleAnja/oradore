package com.example.oradore.android

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.models.Room
import com.example.oradore.models.RoomLocation

@Composable
fun RoomDetailView(room: Room) {
    var roomLocation: RoomLocation? = null
    if (room.roomLocation != null) roomLocation = room.roomLocation
    if (room.roomLocation != null)
        roomLocation?.let { viewWithLocation(room, it) }
    if (roomLocation != null) {
        roomInfo(room, roomLocation.buildingInfo)
    }
}

@Composable
private fun roomInfo(room: Room, buildingInfo: String?) {
    Text(text = room.name)
    if (room.desc != null)
        Text(text = room.desc)
    if (buildingInfo != null)
        Text(buildingInfo)
}

@Composable
private fun viewWithLocation(room: Room, location: RoomLocation) {
    roomInfo(room, location.buildingInfo)
    SubcomposeAsyncImage(
        model = location.url,
        loading = { CircularProgressIndicator() },
        contentDescription = null,
    )
}