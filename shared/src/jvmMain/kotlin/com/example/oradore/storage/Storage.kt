package com.example.oradore.storage

actual typealias Storage = Nothing

actual fun Storage.saveStrings(strings: List<String>, key: String) {}

actual fun Storage.getStrings(key: String): List<String> = emptyList()