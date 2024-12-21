//package com.example.lab4plus.character
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//
//class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {
//    private val _characters = MutableLiveData<List<Character>>()
//    val characters: LiveData<List<Character>> get() = _characters
//
//    private val _error = MutableLiveData<String?>()
//    val error: LiveData<String?> get() = _error
//
//    fun getCharacters(page: Int, pageSize: Int) {
//        viewModelScope.launch {
//            try {
//                val localCharacters = repository.getAllCharacters()
//                if (localCharacters.isNotEmpty()) {
//                    _characters.postValue(localCharacters)
//                } else {
//                    val characterList = repository.fetchCharactersFromApi(page, pageSize)
//                    _characters.postValue(characterList)
//                }
//            } catch (e: Exception) {
//                _error.postValue("Failed to fetch characters: ${e.message}")
//            }
//        }
//    }
//
//    fun refreshCharacters(page: Int, pageSize: Int) {
//        viewModelScope.launch {
//            try {
//                val characterList = repository.fetchCharactersFromApi(page, pageSize)
//                _characters.postValue(characterList)
//            } catch (e: Exception) {
//                _error.postValue("Failed to refresh characters: ${e.message}")
//            }
//        }
//    }
//}
