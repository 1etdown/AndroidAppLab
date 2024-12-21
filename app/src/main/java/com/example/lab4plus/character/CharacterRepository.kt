package com.example.lab4plus.character

import com.example.lab4plus.database.CharacterDao
import com.example.lab4plus.database.CharacterEntity

class CharacterRepository(private val characterDao: CharacterDao) {

     fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters().map { it.toCharacter() }
    }

    // Получение персонажей из API и сохранение их в локальную базу данных
    suspend fun fetchCharactersFromApi(page: Int, pageSize: Int): List<Character> {
        return try {
            // Запрос персонажей из API
            val characters = RetrofitInstance.api.getCharacters(page, pageSize)
            // Преобразование полученных данных в сущности для базы данных
            val entities = characters.map { CharacterEntity.fromCharacter(it) }
            // Сохранение сущностей в локальную базу данных
            characterDao.insertCharacters(entities)
            // Возврат полученных персонажей
            characters
        } catch (e: Exception) {
            // В случае ошибки возвращаем пустой список
            emptyList()
        }
    }
}
