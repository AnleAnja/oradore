package com.example.oradore.android

sealed class DetailScreen(val label: String) {
    object Program : DetailScreen("program")
    object Speaker : DetailScreen("speaker")
    object Room : DetailScreen("room")
    object Favorite : DetailScreen("favorite")
}