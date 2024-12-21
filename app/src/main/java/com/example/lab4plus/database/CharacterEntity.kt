package com.example.lab4plus.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val url: String, // Используем `url` как ключ, чтобы избежать коллизий
    val name: String,
    val culture: String?,
    val born: String?,
    val titles: String,
    val aliases: String,
    val playedBy: String
) {
    // Преобразование из бизнес-логики в сущность для базы данных
    companion object {
        fun fromCharacter(character: com.example.lab4plus.character.Character): CharacterEntity {
            return CharacterEntity(
                url = character.url,
                name = character.name,
                culture = character.culture,
                born = character.born,
                titles = character.titles.joinToString(", "),
                aliases = character.aliases.joinToString(", "),
                playedBy = character.playedBy.joinToString(", ")
            )
        }
    }

    // Преобразование из сущности базы данных в бизнес-логику
    fun toCharacter(): com.example.lab4plus.character.Character {
        return com.example.lab4plus.character.Character(
            url = url,
            name = name,
            culture = culture,
            born = born,
            titles = titles.split(", "),
            aliases = aliases.split(", "),
            playedBy = playedBy.split(", ")
        )
    }
}
