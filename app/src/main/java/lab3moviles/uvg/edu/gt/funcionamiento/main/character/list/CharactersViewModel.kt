package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.repository.LocalCharacterRepository
import lab3moviles.uvg.edu.gt.di.AppDependencies
import lab3moviles.uvg.edu.gt.domain.repository.CharacterRepository

class CharactersViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    fun onEvent(event: CharactersEvent) {
        when (event) {
            CharactersEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            CharactersEvent.RetryClick -> {
                getCharacters()
            }
        }
    }

    private fun getCharacters() {
        getDataJob = viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    isError = false
                )
            }

            val characters = characterRepository.getCharacters()

            _state.update { state ->
                state.copy(
                    isLoading = false,
                    characters = characters
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                CharactersViewModel(
                    characterRepository = LocalCharacterRepository(
                        characterDao = appDatabase.characterDao()
                    )
                )
            }
        }
    }
}