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
import com.example.oradore.android.styling.fontCaption
import com.example.oradore.android.styling.fontHeadline
import com.example.oradore.android.styling.foregroundColorSecondary
import com.example.oradore.android.styling.spacingBetweenText
import com.example.oradore.models.Speaker

@Composable
fun SpeakerListView(
    allSpeakers: List<Speaker>,
    state: MutableState<TextFieldValue>,
    onClick: (Speaker) -> Unit
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
    speaker: Speaker,
    onClick: (Speaker) -> Unit
) {
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
                style = MaterialTheme.fontHeadline(),
                modifier = Modifier.
                padding(
                    bottom = MaterialTheme.spacingBetweenText(),
                    start = 10.dp
                ),
            )
            if (speaker.company.isNotEmpty()) {
                Text(
                    text = speaker.company,
                    style = MaterialTheme.fontCaption(),
                    modifier = Modifier.
                    padding(
                        bottom = MaterialTheme.spacingBetweenText(),
                        start = 10.dp
                    ),
                    color = MaterialTheme.foregroundColorSecondary()
                )
            }
            if (speaker.jobTitle.isNotEmpty()) {
                Text(
                    text = speaker.jobTitle,
                    style = MaterialTheme.fontCaption(),
                    modifier = Modifier.
                    padding(
                        bottom = MaterialTheme.spacingBetweenText(),
                        start = 10.dp
                    ),
                    color = MaterialTheme.foregroundColorSecondary()
                )
            }
        }
    }
}
