//// YourApplication.kt
//package com.example.lab4plus
//
//import android.app.Application
//import com.example.lab4plus.character.CharacterRepository
//import com.example.lab4plus.character.RetrofitInstance
//import com.example.lab4plus.database.AppDatabase
//
//class YourApplication : Application() {
//    val database by lazy { AppDatabase.getDatabase(this) }
//    val repository by lazy {
//        CharacterRepository(
//            characterDao = database.characterDao(),
//            apiService = RetrofitInstance.api
//        )
//    }
//}