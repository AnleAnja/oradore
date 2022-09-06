package com.example.oradore.android.styling

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
  This file mimiks the iOS text styles, colors and spacings
*/

@Composable
fun MaterialTheme.fontTitle() = typography.h5

@Composable
fun MaterialTheme.fontHeadline() = typography.h6 // or subtitle1 with Semibold

@Composable
fun MaterialTheme.fontBody() = typography.body1

@Composable
fun MaterialTheme.fontSubheadline() = typography.subtitle2

@Composable
fun MaterialTheme.fontFootnote() = typography.body2

@Composable
fun MaterialTheme.fontCaption() = typography.caption

@Composable
fun MaterialTheme.spacingBetweenText() = 4.dp

@Composable
fun MaterialTheme.spacingBetweenBlocks() = 16.dp

@Composable
fun MaterialTheme.paddingDefault() = 8.dp

@Composable
fun MaterialTheme.iconSize() = 26.dp

@Composable
fun MaterialTheme.iconButtonSize() = 34.dp

@Composable
fun MaterialTheme.foregroundColorSecondary() = Color.Gray

@Composable
fun MaterialTheme.primaryColor() = colors.primary