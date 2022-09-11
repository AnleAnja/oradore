package com.example.oradore.storage

expect class Storage

expect fun Storage.saveStrings(strings: List<String>, key: String)

expect fun Storage.getStrings(key: String): List<String>