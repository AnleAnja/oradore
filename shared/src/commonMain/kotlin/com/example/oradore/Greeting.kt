package com.example.oradore

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}