package com.example.oradore.android

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.oradore.android.styling.iconButtonSize
import com.example.oradore.models.ProgramEntry

private val color = Color(0xFFFFA500)

@Composable
fun FavoriteIconButtonView(
    programEntryId: String,
    viewModel: AppViewModel
) {
    IconButton(
        modifier = Modifier
            .size(MaterialTheme.iconButtonSize()),
        onClick = { viewModel.toggleFav(programEntryId) }
    ) {
        FavoriteIconVIew(programEntryId, viewModel)
    }
}

@Composable
fun FavoriteIconVIew(
    programEntryId: String,
    viewModel: AppViewModel
) {
    if (viewModel.isFavorite(programEntryId)) {
        Icon(
            Icons.Filled.Star,
            contentDescription = "add program to favorite",
            tint = color,
        )
    } else {
        Icon(
            Icons.Default.StarBorder,
            contentDescription = "remove program from favorite",
            tint = color,
        )
    }
}