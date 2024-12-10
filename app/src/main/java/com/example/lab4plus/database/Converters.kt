//package com.example.lab4plus.database
//
//// Converters.kt
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//class Converters {
//
//    @TypeConverter
//    fun fromList(list: List<String>): String {
//        return Gson().toJson(list)
//    }
//
//    @TypeConverter
//    fun toList(data: String): List<String> {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(data, listType)
//    }
//}