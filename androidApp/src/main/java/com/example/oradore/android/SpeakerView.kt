package com.example.oradore.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.models.Room
import com.example.oradore.models.SpeakerPreview

@Composable
fun SpeakerListView(
    allSpeakers: List<SpeakerPreview>,
    state: MutableState<TextFieldValue>,
    onClick: (SpeakerPreview) -> Unit
) {
    val searchedText = state.value.text.lowercase()

    val visibleSpeakers = if (searchedText.isEmpty()) {
        allSpeakers
    } else {
        allSpeakers.filter {
            it.firstName.lowercase().contains(searchedText) ||
                    it.lastName.lowercase().contains(searchedText) ||
                    it.company.lowercase().contains(searchedText) ||
                    it.jobTitle.lowercase().contains(searchedText)
        }
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(visibleSpeakers) { speaker ->
            SpeakerView(speaker, onClick)
        }
    }
}

@Composable
fun SpeakerView(
    speaker: SpeakerPreview,
    onClick: (SpeakerPreview) -> Unit
) {
    val typo = MaterialTheme.typography
    Row(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .clickable(onClick = { onClick(speaker) })
            .fillMaxWidth(),
    ) {
        SubcomposeAsyncImage(
            model = speaker.imgPreview,
            loading = { CircularProgressIndicator() },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )
        Column {
            Text(
                text = "${speaker.firstName} ${speaker.lastName}",
                style = typo.body1,
                modifier = Modifier.padding(bottom = 4.dp, start = 10.dp),
            )
            if (speaker.company.isNotEmpty()) {
                Text(
                    text = speaker.company,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = 4.dp, start = 10.dp),
                    color = Color.Gray
                )
            }
            if (speaker.jobTitle.isNotEmpty()) {
                Text(
                    text = speaker.jobTitle,
                    style = typo.caption,
                    modifier = Modifier.padding(bottom = 4.dp, start = 10.dp),
                    color = Color.Gray
                )
            }
        }
    }
}
