package com.example.oradore.storage

class FavoritesStorage(private val storage: Storage) {
    private val key = "com.example.oradore.favorites"

    fun saveFavorites(favorites: List<String>) =
        storage.saveStrings(favorites, key)

    fun getFavorites(): List<String> =
        storage.getStrings(key)
}