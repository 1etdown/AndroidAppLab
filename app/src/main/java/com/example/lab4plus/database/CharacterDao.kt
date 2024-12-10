//package com.example.lab4plus.database
//
//// package com.example.yourapp.database
//
//import androidx.room.*
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface CharacterDao {
//
//    @Query("SELECT * FROM characters")
//    fun getAllCharacters(): Flow<List<CharacterEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCharacters(characters: List<CharacterEntity>)
//
//    @Update
//    suspend fun updateCharacter(character: CharacterEntity)
//
//    @Delete
//    suspend fun deleteCharacter(character: CharacterEntity)
//
//    @Query("DELETE FROM characters")
//    suspend fun deleteAllCharacters()
//}