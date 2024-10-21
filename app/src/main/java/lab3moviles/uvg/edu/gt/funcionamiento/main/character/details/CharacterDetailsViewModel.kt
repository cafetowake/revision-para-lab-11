package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.repository.LocalCharacterRepository
import lab3moviles.uvg.edu.gt.repository.CharacterRepository

class CharacterDetailsViewModel(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val characterProfile = savedStateHandle.toRoute<CharacterDetailsDestination>()
    private val _state: MutableStateFlow<CharacterDetailsState> = MutableStateFlow(CharacterDetailsState())
    val state = _state.asStateFlow()

    init {
        getCharacterData()
    }

    private fun getCharacterData() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(isLoading = true)
            }

            val location = characterRepository.getCharacterById(characterProfile.characterId)

            _state.update { state ->
                state.copy(
                    data = location,
                    isLoading = false
                )
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                CharacterDetailsViewModel(
                    characterRepository = LocalCharacterRepository(),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
