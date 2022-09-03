package com.example.oradore.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.oradore.models.Room

@Composable
fun RoomListView(
    allRooms: List<Room>,
    state: MutableState<TextFieldValue>,
    onClick: (Room) -> Unit
) {
    val searchedText = state.value.text.lowercase()

    val visibleRooms = if (searchedText.isEmpty()) {
        allRooms
    } else {
        allRooms.filter { it.name.lowercase().contains(searchedText) }
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(visibleRooms) { room ->
            RoomView(room, onClick)
        }
    }
}

@Composable
fun RoomView(
    room: Room,
    onClick: (Room) -> Unit
) {
    Text(
        text = room.name,
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .clickable(onClick = { onClick(room) })
            .padding(8.dp)
    )
}