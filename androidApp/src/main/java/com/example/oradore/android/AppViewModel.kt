package com.example.oradore.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class AppViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var screen by mutableStateOf(0)
}