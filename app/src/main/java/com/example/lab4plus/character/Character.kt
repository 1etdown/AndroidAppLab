// Character.kt
package com.example.lab4plus.character

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val url: String,
    val name: String,
    val culture: String? = null,
    val born: String? = null,
    val titles: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    val playedBy: List<String> = emptyList()
)