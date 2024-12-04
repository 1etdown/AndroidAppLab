// HomeFragment.kt
package com.example.lab4plus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4plus.character.CharacterAdapter
import com.example.lab4plus.databinding.FragmentHomeBinding
import com.example.lab4plus.character.CharacterViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import android.util.Log

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")
    private lateinit var viewModel: CharacterViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.chatListView.layoutManager = layoutManager

        // Добавляем разделитель
        val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.chatListView.addItemDecoration(divider)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter = CharacterAdapter(characters)
            binding.chatListView.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
        }

        // Запрашиваем персонажей со страницы 2, по 50 персонажей на страницу (51-100)
        viewModel.getCharacters(page = 2, pageSize = 50)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}