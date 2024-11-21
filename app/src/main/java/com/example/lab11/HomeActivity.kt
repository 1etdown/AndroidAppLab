package com.example.lab11

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(R.layout.activity_home)

        val chatListView = findViewById<ListView>(R.id.chatListView)
        val chats = arrayOf(
            "Дима: Видешь аккумуляторы?",
            "Паша: Грузи аккумуляторы",
            "Костя: Я не буду трогать твои аккумуляторы!"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, chats)
        chatListView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}
