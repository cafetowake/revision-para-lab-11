package lab3moviles.uvg.edu.gt.funcionamiento.main.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.data.CharacterDb
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.repository.CharacterRepository

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _characters = MutableStateFlow<List<Person>>(emptyList())
    val characters: StateFlow<List<Person>> = _characters

    init {
        syncCharacters()
    }

    private fun syncCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            val charactersFromDb = characterRepository.getCharacters()
            if (charactersFromDb.isEmpty()) {
                characterRepository.syncCharacters(CharacterDb.getCharacterById())
            }
            _characters.value = characterRepository.getCharacters()
            _isLoading.value = false
        }
    }
}
