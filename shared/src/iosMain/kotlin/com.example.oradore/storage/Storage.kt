package com.example.oradore.storage

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias Storage = NSObject

actual fun Storage.saveStrings(strings: List<String>, key: String) {
    NSUserDefaults.standardUserDefaults.setObject(strings, key)
}

@Suppress("UNCHECKED_CAST")
actual fun Storage.getStrings(key: String): List<String> =
    NSUserDefaults.standardUserDefaults.stringArrayForKey(key) as? List<String> ?: emptyList()