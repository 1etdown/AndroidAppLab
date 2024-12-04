// CharacterRepository.kt
package com.example.lab4plus.character

import android.util.Log

class CharacterRepository {
    suspend fun fetchCharacters(page: Int, pageSize: Int): List<Character> {
        return try {
            val response = RetrofitInstance.api.getCharacters(page, pageSize)
            Log.d("CharacterRepository", "Fetched page $page: ${response.size} characters")
            response
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters", e)
            emptyList()
        }
    }
}