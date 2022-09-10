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
import com.example.oradore.android.styling.*
import com.example.oradore.models.*

@Composable
fun ProgramDetailView(
    entry: ProgramEntry,
    viewModel: AppViewModel
) {
    Column(
        Modifier
            .padding(MaterialTheme.paddingDefault())
            .verticalScroll(rememberScrollState())
    ) { // VStack
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacingBetweenText())
        ) {
            Text(
                text = entry.format.label,
                style = MaterialTheme.fontFootnote(),
                color = entry.format.hexColor.color,
                fontWeight = FontWeight.Bold
            )
            FavoriteIconButtonView(entry.id, viewModel)
        }
        Text(
            text = entry.name,
            style = MaterialTheme.fontHeadline(),
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacingBetweenText())
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacingBetweenText())
        ) {
            Icon(
                Icons.Outlined.Schedule,
                contentDescription = "time",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(MaterialTheme.iconSize())
            )
            Text(
                text = entry.timeRange.formated,
                style = MaterialTheme.fontFootnote(),
                modifier = Modifier.padding(start = MaterialTheme.spacingBetweenText())
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = MaterialTheme.spacingBetweenText())
        ) {
            Icon(
                Icons.Outlined.Room,
                contentDescription = "time",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(MaterialTheme.iconSize())
            )
            Text(
                text = entry.room.name,
                style = MaterialTheme.fontFootnote(),
                modifier = Modifier.padding(bottom = MaterialTheme.spacingBetweenText())
            )
        }
        Text(
            text = entry.description,
            style = MaterialTheme.fontBody(),
        )
        Divider(
            thickness = 2.dp,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.paddingDefault(),
                    bottom = MaterialTheme.paddingDefault()
                )
        )
        if(entry.speakers.any { it.second == Role.SPEAKER }) {
            Text(
                text = "Speaker",
                style = MaterialTheme.fontTitle(),
                modifier = Modifier
                    .padding(
                        bottom = MaterialTheme.spacingBetweenText(),
                        top = MaterialTheme.paddingDefault()
                    )
            )
            entry.speakers.filter { it.second == Role.SPEAKER }.forEachIndexed { index, speaker ->
                SpeakerPreviewView(speaker, index == entry.speakers.size - 1)
            }
        }
        if(entry.speakers.any { it.second == Role.MODERATOR }) {
            Text(
                text = "Moderation",
                style = MaterialTheme.fontTitle(),
                modifier = Modifier
                    .padding(
                        bottom = MaterialTheme.spacingBetweenText(),
                        top = MaterialTheme.paddingDefault()
                    )
            )
            entry.speakers.filter { it.second == Role.MODERATOR }.forEachIndexed { index, speaker ->
                SpeakerPreviewView(speaker, index == entry.speakers.size - 1)
            }
        }
    }
}