package com.example.oradore.color

data class Color internal constructor(val hex: String) {
    companion object {
        val darkBlue = Color("#30549b")
        val lightBlue = Color("#439edd")
        val purple = Color("#a2418a")

        val primary = darkBlue
        val secondary = purple
        val ternary = lightBlue
    }
}