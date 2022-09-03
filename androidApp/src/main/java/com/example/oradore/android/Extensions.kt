package com.example.oradore.android

import com.example.oradore.models.TimeRange
import java.text.SimpleDateFormat
import java.util.*

val TimeRange.formated: String get() {
    val f = SimpleDateFormat("HH:mm")
    val s = f.format(Date(start))
    val e = f.format(Date(end))
    return "$s - $e Uhr"
}

val Long.formated: String get() {
    val f = SimpleDateFormat("HH:mm")
    val s = f.format(Date(this))
    return "$s Uhr"
}