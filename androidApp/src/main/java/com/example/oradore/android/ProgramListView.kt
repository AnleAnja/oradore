package com.example.oradore.android

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.api.GroupProgramEntries
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.Role
import com.example.oradore.models.Speaker

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgramListView(
    allProgramEntries: List<GroupProgramEntries>,
    state: MutableState<TextFieldValue>,
    viewModel: AppViewModel,
    onClick: (ProgramEntry) -> Unit
) {
    val searchedText = state.value.text.lowercase()

    val visibleProgramEntries = if (searchedText.isEmpty()) {
        allProgramEntries
    } else {
        viewModel.filterProgramEntries { entry ->
            containsSearchText(entry, searchedText)
        }
    }

    val collapsed = remember { mutableStateOf(emptySet<Long>()) }

    LazyColumn( // LazyVStack / List
        modifier = Modifier.fillMaxWidth()
    ) {
        visibleProgramEntries
            .forEach { (start, entries) ->
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .background(Color.White)
                            .height(25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "··· ab ${start.formated} ···",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(30.dp),
                            onClick = {
                                if (collapsed.value.contains(start)) {
                                    collapsed.value -= start
                                } else {
                                    collapsed.value += start
                                }
                            },
                        ) {
                            val icon =
                                if (collapsed.value.contains(start)) Icons.Filled.KeyboardArrowRight
                                else Icons.Filled.KeyboardArrowDown
                            Icon(
                                icon,
                                contentDescription = "collapse program entries",
                                tint = MaterialTheme.colors.primary,
                            )
                        }
                    }
                }
                if (!collapsed.value.contains(start)) {
                    items(entries) { entry -> // ForEach
                        ProgramEntryPreviewView(entry, viewModel, onClick)
                    }
                }
            }
    }
}

@Composable
private fun ProgramEntryPreviewView(
    programEntry: ProgramEntry,
    viewModel: AppViewModel,
    onClick: (ProgramEntry) -> Unit
) {
    val typo = MaterialTheme.typography

    Row( // HStack
        modifier = Modifier
            .clickable(onClick = { onClick(programEntry) })
            .padding(8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Divider(
            color = programEntry.format.hexColor.color,
            modifier = Modifier
                .fillMaxHeight(1f)
                .width(2.dp)
        )
        Column(Modifier.padding(start = 8.dp)) { // VStack
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = programEntry.format.label,
                    style = typo.body2,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = programEntry.format.hexColor.color,
                    fontWeight = FontWeight.Bold
                )
                FavoriteIconVIew(programEntry.id, viewModel)
            }

            Text(
                text = "${programEntry.timeRange.formated} in ${programEntry.room.name}",
                style = typo.body2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = programEntry.name,
                style = typo.h6,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            programEntry.speakers.forEachIndexed { index, speaker ->
                SpeakerPreviewView(speaker, index == programEntry.speakers.size - 1)
            }
        }
    }

}

@Composable
private fun SpeakerPreviewView(speakers: Pair<Speaker, Role>, isLast: Boolean) {
    val typo = MaterialTheme.typography
    val speaker = speakers.first

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "${speaker.firstName} ${speaker.lastName}",
                style = typo.body1,
                modifier = Modifier.padding(bottom = 4.dp),
            )
            if (speaker.company.isNotEmpty()) {
                Text(
                    text = speaker.company,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = Color.Gray
                )
            }
            if (speaker.jobTitle.isNotEmpty()) {
                Text(
                    text = speaker.jobTitle,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = if (isLast) 0.dp else 4.dp),
                    color = Color.Gray
                )
            }
        }
        SubcomposeAsyncImage(
            model = speaker.imgPreview,
            loading = { CircularProgressIndicator() },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(55.dp)
        )
    }
}

private fun containsSearchText(entry: ProgramEntry, searchText: String): Boolean =
    entry.name.lowercase().contains(searchText) ||
            entry.room.name.lowercase().contains(searchText) ||
            entry.speakers.any { speaker ->
                speaker.first.firstName.lowercase().contains(searchText) ||
                        speaker.first.lastName.lowercase().contains(searchText) ||
                        speaker.first.company.lowercase().contains(searchText) ||
                        speaker.first.jobTitle.lowercase().contains(searchText)
            } ||
            entry.format.label.lowercase().contains(searchText)