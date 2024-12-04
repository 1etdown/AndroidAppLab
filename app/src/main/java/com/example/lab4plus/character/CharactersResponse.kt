// CharactersResponse.kt
package com.example.lab4plus.character

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val data: List<Character>
)