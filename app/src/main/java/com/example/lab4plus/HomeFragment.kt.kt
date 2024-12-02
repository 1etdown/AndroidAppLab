package com.example.lab4plus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.lab4plus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chats = arrayOf(
            "Дима: Видишь аккумуляторы?",
            "Паша: Грузи аккумуляторы",
            "Костя: Я не буду трогать твои аккумуляторы!"
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, chats)
        binding.chatListView.adapter = adapter
        //рейсайкл вью и добавить в биндинг
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
