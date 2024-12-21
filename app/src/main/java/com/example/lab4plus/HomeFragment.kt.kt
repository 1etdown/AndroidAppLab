package com.example.lab4plus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4plus.character.CharacterAdapter
import com.example.lab4plus.character.CharacterRepository
import com.example.lab4plus.database.AppDatabase
import com.example.lab4plus.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")
    private lateinit var adapter: CharacterAdapter
    private lateinit var repository: CharacterRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация базы данных и репозитория
        val database = AppDatabase.getDatabase(requireContext())
        val characterDao = database.characterDao()
        repository = CharacterRepository(characterDao)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.chatListView.layoutManager = layoutManager

        val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.chatListView.addItemDecoration(divider)

        // Получение персонажей
        fetchCharacters(2, 50)

        binding.settingsButton.setOnClickListener {
            // Навигация к настройкам
        }

        binding.saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val heroes = repository.getAllCharacters()
                saveHeroesToFile(heroes)
            }
        }
    }

    private fun fetchCharacters(page: Int, pageSize: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val localCharacters = repository.getAllCharacters()
            if (localCharacters.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    updateUI(localCharacters)
                }
            } else {
                val characters = repository.fetchCharactersFromApi(page, pageSize)
                withContext(Dispatchers.Main) {
                    updateUI(characters)
                }
            }
        }
    }

    private fun updateUI(characters: List<com.example.lab4plus.character.Character>) {
        if (::adapter.isInitialized) {
            adapter.updateData(characters)
        } else {
            adapter = CharacterAdapter(characters)
            binding.chatListView.adapter = adapter
        }
    }

    private fun saveHeroesToFile(heroes: List<com.example.lab4plus.character.Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            val fileName = "heroes_list.txt"
            val file = File(requireContext().getExternalFilesDir(null), "Documents/$fileName")

            try {
                FileOutputStream(file).use { fos ->
                    heroes.forEach { hero ->
                        fos.write("${hero.name}\n".toByteArray())
                    }
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Файл сохранён: $fileName", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Ошибка при сохранении файла", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
