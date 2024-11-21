package com.example.lab11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class `HomeFragment` : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val chatListView: ListView = view.findViewById(R.id.chatListView)
        val chats = arrayOf(
            "Дима: Видишь аккумуляторы?",
            "Паша: Грузи аккумуляторы",
            "Костя: Я не буду трогать твои аккумуляторы!"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, chats)
        chatListView.adapter = adapter

        return view
    }
}
