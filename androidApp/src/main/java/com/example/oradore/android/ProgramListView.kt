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
import com.example.oradore.models.ProgramEntryPreview
import com.example.oradore.models.SpeakerPreview
import com.example.oradore.models.TimeRange
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgramListView(
    allProgramEntries: List<ProgramEntryPreview>,
    state: MutableState<TextFieldValue>,
    onClick: (ProgramEntryPreview) -> Unit
) {
    val searchedText = state.value.text.lowercase()

    val visibleProgramEntries = if (searchedText.isEmpty()) {
        allProgramEntries
    } else {
        allProgramEntries.filter { entry ->
            containsSearchText(entry, searchedText)
        }
    }

    val entries = visibleProgramEntries
        .groupBy { it.timeRange.start }

    val collapsed = remember { mutableStateOf(emptySet<Long>()) }

    LazyColumn( // LazyVStack / List
        modifier = Modifier.fillMaxWidth()
    ) {
        entries
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
                            text = "··· ab ${timestampLabel(start)} ···",
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
                                contentDescription = "Localized description",
                                tint = MaterialTheme.colors.primary,
                            )
                        }
                    }
                }
                if (!collapsed.value.contains(start)) {
                    items(entries) { entry -> // ForEach
                        ProgramEntryPreviewView(entry, onClick)
                    }
                }
            }
    }
}

@Composable
fun ProgramEntryPreviewView(
    programEntry: ProgramEntryPreview,
    onClick: (ProgramEntryPreview) -> Unit
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
            Text(
                text = programEntry.format.label,
                style = typo.body2,
                modifier = Modifier.padding(bottom = 4.dp),
                color = programEntry.format.hexColor.color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${timeRangeLabel(programEntry.timeRange)} in ${programEntry.room.name}",
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
fun SpeakerPreviewView(speaker: SpeakerPreview, isLast: Boolean) {
    val typo = MaterialTheme.typography

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

private fun timeRangeLabel(timeRange: TimeRange): String {
    val f = SimpleDateFormat("HH:mm")
    val s = f.format(Date(timeRange.start))
    val e = f.format(Date(timeRange.end))
    return "$s - $e Uhr"
}

private fun timestampLabel(timestamp: Long): String {
    val f = SimpleDateFormat("HH:mm")
    val s = f.format(Date(timestamp))
    return "$s Uhr"
}

private fun containsSearchText(entry: ProgramEntryPreview, searchText: String): Boolean =
    entry.name.lowercase().contains(searchText) ||
            entry.room.name.lowercase().contains(searchText) ||
            entry.speakers.any { speaker ->
                speaker.firstName.lowercase().contains(searchText) ||
                        speaker.lastName.lowercase().contains(searchText) ||
                        speaker.company.lowercase().contains(searchText) ||
                        speaker.jobTitle.lowercase().contains(searchText)
            } ||
            entry.format.label.lowercase().contains(searchText)