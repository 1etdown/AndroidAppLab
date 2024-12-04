// ApiService.kt
package com.example.lab4plus.character

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Character>
}