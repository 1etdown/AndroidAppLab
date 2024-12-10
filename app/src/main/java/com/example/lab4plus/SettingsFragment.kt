package com.example.lab4plus

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.lab4plus.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch
import java.io.File

private val Context.dataStore by preferencesDataStore("settings")

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")

    private val darkModeKey = booleanPreferencesKey("dark_mode")
    private val fontSizeKey = booleanPreferencesKey("large_font")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDataStoreObservers()
        setupFileManagementButtons()
    }

    private fun setupDataStoreObservers() {
        lifecycleScope.launch {
            requireContext().dataStore.data.collect { prefs ->
                val isDarkMode = prefs[darkModeKey] ?: false
                val isLargeFont = prefs[fontSizeKey] ?: false

                binding.darkModeSwitch.isChecked = isDarkMode
                binding.fontSizeSwitch.isChecked = isLargeFont

                AppCompatDelegate.setDefaultNightMode(
                    if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
                updateFontSize(isLargeFont)
            }
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                requireContext().dataStore.edit { prefs ->
                    prefs[darkModeKey] = isChecked
                }
            }
        }

        binding.fontSizeSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                requireContext().dataStore.edit { prefs ->
                    prefs[fontSizeKey] = isChecked
                }
            }
        }
    }

    private fun updateFontSize(isLarge: Boolean) {
        val newConfig = Configuration(resources.configuration)
        newConfig.fontScale = if (isLarge) 2f else 1.0f
        requireActivity().resources.updateConfiguration(newConfig, requireActivity().resources.displayMetrics)
        Toast.makeText(requireContext(), "Размер шрифта обновлен", Toast.LENGTH_SHORT).show()
    }

    private fun setupFileManagementButtons() {
        binding.checkFileButton.setOnClickListener {
            if (checkFileExistence()) {
                Toast.makeText(requireContext(), "Файл найден", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Файл не найден", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteFileButton.setOnClickListener {
            deleteFile()
        }

        binding.restoreFileButton.setOnClickListener {
            restoreFileFromBackup()
        }
    }

    private fun checkFileExistence(): Boolean {
        val file = File(requireContext().getExternalFilesDir(null), "Documents/heroes_list.txt")
        return file.exists()
    }

    private fun deleteFile() {
        val file = File(requireContext().getExternalFilesDir(null), "Documents/heroes_list.txt")
        if (file.exists()) {
            val deleted = file.delete()
            if (deleted) {
                Toast.makeText(requireContext(), "Файл удалён", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Не удалось удалить файл", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Файл не найден", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restoreFileFromBackup() {
        val backupFile = File(requireContext().filesDir, "backup_heroes_list.txt")
        val externalFile = File(requireContext().getExternalFilesDir(null), "Documents/heroes_list.txt")

        if (backupFile.exists()) {
            backupFile.copyTo(externalFile, overwrite = true)
            Toast.makeText(requireContext(), "Файл восстановлен", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Резервная копия не найдена", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
