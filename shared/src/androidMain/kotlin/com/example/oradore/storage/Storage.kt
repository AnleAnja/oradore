package com.example.oradore.storage

import android.app.Activity

actual typealias Storage = Activity

actual fun Storage.saveStrings(strings: List<String>, key: String) {
    val prefs = getPreferences(Activity.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putStringSet(key, strings.toMutableSet())
    editor.apply()
}

actual fun Storage.getStrings(key: String): List<String> =
    getPreferences(Activity.MODE_PRIVATE).getStringSet(key, emptySet())?.toList() ?: emptyList()