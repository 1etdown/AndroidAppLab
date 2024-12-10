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
import androidx.navigation.fragment.findNavController
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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

        val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.chatListView.addItemDecoration(divider)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter = CharacterAdapter(characters)
            binding.chatListView.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
        }

        viewModel.getCharacters(page = 2, pageSize = 50)

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        binding.saveButton.setOnClickListener {
            viewModel.characters.value?.let { heroes ->
                saveHeroesToFile(heroes)
            }
        }
    }

    private fun saveHeroesToFile(heroes: List<com.example.lab4plus.character.Character>) {
        val fileName = "heroes_list.txt"
        val file = File(requireContext().getExternalFilesDir(null), "Documents/$fileName")

        try {
            FileOutputStream(file).use { fos ->
                heroes.forEach { hero ->
                    fos.write("${hero.name}\n".toByteArray())
                }
            }
            Toast.makeText(requireContext(), "Файл сохранён: $fileName", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(requireContext(), "Ошибка при сохранении файла", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}