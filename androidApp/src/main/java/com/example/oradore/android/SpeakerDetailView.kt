package com.example.oradore.android

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.oradore.android.styling.*
import com.example.oradore.models.Speaker

@Composable
fun SpeakerDetailView(
    speaker: Speaker
) {
    Column(
        Modifier
            .padding(MaterialTheme.paddingDefault())
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        SpeakerImageView(speaker)
        SpeakerBusinessDataView(speaker)
        SpeakerInfoView(speaker)
        SpeakerBioView(speaker)
    }
}

@Composable
private fun SpeakerImageView(speaker: Speaker) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    val maxImageHeight = height.times(0.5f)

    SubcomposeAsyncImage(
        model = speaker.imgLarge,
        loading = { CircularProgressIndicator() },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = maxImageHeight)
    )
}

@Composable
private fun SpeakerBioView(
    speaker: Speaker
) {
    if (speaker.bio.isEmpty()) return

    Divider(
        thickness = 2.dp,
        modifier = Modifier.padding(top = 8.dp)
    )

    Text(
        text = speaker.bio,
        style = MaterialTheme.fontBody(),
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun SpeakerInfoView(
    speaker: Speaker
) {
    if (speaker.location.isNotEmpty() || speaker.website.isNotEmpty()) {
        Spacer(modifier = Modifier.padding(top = MaterialTheme.spacingBetweenBlocks()))
    }
    InfoRow(
        speaker.location,
        "location of speaker",
        Icons.Outlined.PinDrop
    )

    if (speaker.website.isNotEmpty()) {
        val context = LocalContext.current

        InfoRow(
            speaker.website,
            "webseite of speaker",
            Icons.Outlined.Link,
            Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, speaker.website.parseToUri())
                context.startActivity(intent)
            }
        )
    }
}

@Composable
private fun InfoRow(
    content: String,
    contentDescription: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    if (content.isEmpty()) return

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(MaterialTheme.spacingBetweenText())
            .then(modifier)
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.primaryColor(),
            modifier = Modifier.size(MaterialTheme.iconSize()),
        )
        Text(
            text = content,
            style = MaterialTheme.fontCaption(),
            modifier = Modifier.padding(start = MaterialTheme.spacingBetweenText())
        )
    }
}

@Composable
private fun SpeakerBusinessDataView(
    speaker: Speaker
) {
    Text(
        text = "${speaker.firstName} ${speaker.lastName}",
        style = MaterialTheme.fontTitle(),
        modifier = Modifier
            .padding(top = MaterialTheme.spacingBetweenBlocks())
    )
    if (speaker.company.isNotEmpty()) {
        Text(
            text = speaker.company,
            style = MaterialTheme.fontSubheadline(),
            modifier = Modifier
                .padding(top = MaterialTheme.spacingBetweenText())
        )
    }
    if (speaker.jobTitle.isNotEmpty()) {
        Text(
            text = speaker.jobTitle,
            style = MaterialTheme.fontSubheadline(),
            modifier = Modifier
                .padding(top = MaterialTheme.spacingBetweenText())
        )
    }
}