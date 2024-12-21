package com.example.lab4plus.database

import androidx.room.*

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
     fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE url = :url")
     fun getCharacterByUrl(url: String): CharacterEntity?

    @Update
    suspend fun updateCharacter(character: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}
