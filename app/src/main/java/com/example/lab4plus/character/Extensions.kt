//// Extensions.kt
//package com.example.lab4plus.character
//import com.example.lab4plus.character.Character
//import com.example.lab4plus.database.CharacterEntity
//
//fun Character.toEntity(): CharacterEntity {
//    return CharacterEntity(
//        id = url?.substringAfterLast("/")?.toIntOrNull() ?: 0,
//        name = name,
//        culture = culture,
//        born = born,
//        titles = titles,
//        aliases = aliases,
//        playedBy = playedBy
//    )
//}