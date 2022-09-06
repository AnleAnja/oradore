package com.example.oradore.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.models.Room
import com.example.oradore.models.RoomLocation

@Composable
fun RoomDetailView(room: Room) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    val maxImageHeight = height.times(0.8f)

    if (room.roomLocation != null) {
        RoomInfoWithLocationView(room, room.roomLocation!!, maxImageHeight)
    } else {
        RoomInfoView(room, null)
    }
}

@Composable
private fun RoomInfoView(room: Room, buildingInfo: String?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth())
        Text(
            text = room.name,
            style = MaterialTheme.typography.h5
        )
        if (room.desc.isNotEmpty())
            Text(
                text = room.desc,
                style = MaterialTheme.typography.subtitle2
            )
        if (buildingInfo != null)
            Text(
                text = buildingInfo,
                style = MaterialTheme.typography.subtitle2
            )
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun RoomInfoWithLocationView(room: Room, location: RoomLocation, maxImageHeight: Dp) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        RoomInfoView(room, location.buildingInfo)
        SubcomposeAsyncImage(
            model = location.url,
            loading = { CircularProgressIndicator() },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxHeight = maxImageHeight)
        )
    }
}