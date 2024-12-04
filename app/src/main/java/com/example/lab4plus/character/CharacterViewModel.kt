// CharacterViewModel.kt
package com.example.lab4plus.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log

class CharacterViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val repository = CharacterRepository()

    fun getCharacters(page: Int, pageSize: Int) {
        Log.d("CharacterViewModel", "Fetching characters from page $page with pageSize $pageSize...")
        viewModelScope.launch {
            try {
                val characterList = repository.fetchCharacters(page, pageSize)
                Log.d("CharacterViewModel", "Characters fetched: ${characterList.size}")
                _characters.postValue(characterList)
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Failed to fetch characters", e)
                _error.postValue("Failed to fetch characters: ${e.message}")
            }
        }
    }
}