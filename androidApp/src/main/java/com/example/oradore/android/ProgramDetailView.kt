package com.example.oradore.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.models.*

@Composable
fun ProgramDetailView(
    entry: ProgramEntry,
    viewModel: AppViewModel
) {
    val typo = MaterialTheme.typography

    Column(
        Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) { // VStack
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = entry.format.label,
                style = typo.body1,
                color = entry.format.hexColor.color,
                fontWeight = FontWeight.Bold
            )
            FavoriteIconButtonView(entry.id, viewModel)
        }
        Text(
            text = entry.name,
            style = typo.h5,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Icon(
                Icons.Outlined.Schedule,
                contentDescription = "time",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = entry.timeRange.formated,
                style = typo.body2,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Icon(
                Icons.Outlined.Room,
                contentDescription = "time",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = entry.room.name,
                style = typo.body2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Text(
            text = entry.description,
            style = typo.body1,
        )
        if(entry.speakers.any { it.second == Role.SPEAKER }) {
            Text(
                text = "Speaker",
                style = typo.h5,
                modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
            )
            entry.speakers.filter { it.second == Role.SPEAKER }.forEachIndexed { index, speaker ->
                SpeakerPreviewView(speaker, index == entry.speakers.size - 1)
            }
        }
        if(entry.speakers.any { it.second == Role.MODERATOR }) {
            Text(
                text = "Moderation",
                style = typo.h5,
                modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
            )
            entry.speakers.filter { it.second == Role.MODERATOR }.forEachIndexed { index, speaker ->
                SpeakerPreviewView(speaker, index == entry.speakers.size - 1)
            }
        }
    }
}

@Composable
private fun SpeakerPreviewView(speaker: Pair<Speaker, Role>, isLast: Boolean) {
    val typo = MaterialTheme.typography
    val speakerProperties = speaker.first

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        SubcomposeAsyncImage(
            model = speakerProperties.imgPreview,
            loading = { CircularProgressIndicator() },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(65.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "${speakerProperties.firstName} ${speakerProperties.lastName}",
                style = typo.body1,
                modifier = Modifier.padding(bottom = 4.dp),
            )
            if (speakerProperties.company.isNotEmpty()) {
                Text(
                    text = speakerProperties.company,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = Color.Gray
                )
            }
            if (speakerProperties.jobTitle.isNotEmpty()) {
                Text(
                    text = speakerProperties.jobTitle,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = if (isLast) 0.dp else 4.dp),
                    color = Color.Gray
                )
            }
        }
    }
}