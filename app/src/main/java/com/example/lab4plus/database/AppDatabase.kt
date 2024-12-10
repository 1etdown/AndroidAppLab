//// AppDatabase.kt
//package com.example.lab4plus.character
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.example.lab4plus.database.CharacterDao
//import com.example.lab4plus.database.CharacterEntity
//import com.example.lab4plus.database.Converters
//
//@Database(entities = [CharacterEntity::class], version = 1)
//@TypeConverters(Converters::class)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun characterDao(): CharacterDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "characters_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}