// Character.kt
package com.example.lab4plus.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val url: String? = null,
    val name: String,
    val gender: String? = null,
    val culture: String? = null,
    val born: String? = null,
    val died: String? = null,
    val titles: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    val father: String? = null,
    val mother: String? = null,
    val spouse: String? = null,
    val allegiances: List<String> = emptyList(),
    val books: List<String> = emptyList(),
    val povBooks: List<String> = emptyList(),
    val tvSeries: List<String> = emptyList(),
    val playedBy: List<String> = emptyList()
)