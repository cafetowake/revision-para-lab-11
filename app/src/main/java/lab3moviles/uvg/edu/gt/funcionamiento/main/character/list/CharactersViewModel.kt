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
import lab3moviles.uvg.edu.gt.data.network.KtorShowApi
import lab3moviles.uvg.edu.gt.data.network.dto.character.mapToCharacterModel
import lab3moviles.uvg.edu.gt.di.KtorDependencies
import lab3moviles.uvg.edu.gt.domain.Api.ShowApi
import lab3moviles.uvg.edu.gt.domain.network.util.map
import lab3moviles.uvg.edu.gt.domain.network.util.onError
import lab3moviles.uvg.edu.gt.domain.network.util.onSuccess

class CharactersViewModel(
    private val showApi: ShowApi
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
        viewModelScope.launch {
            showApi
                .getAllCharacters()
                .map { response -> response.data.map { it.mapToCharacterModel() } }
                .onSuccess { characters ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            characters = characters
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorShowApi(KtorDependencies.provideHttpClient(context))
                CharactersViewModel(api)
            }

        }
    }
}