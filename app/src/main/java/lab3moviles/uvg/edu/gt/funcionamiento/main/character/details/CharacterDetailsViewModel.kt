package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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

class CharacterDetailsViewModel(
    private val showApi: ShowApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val characterId: Int = savedStateHandle["characterId"] ?: throw IllegalArgumentException("Character ID is missing")
    private val _state: MutableStateFlow<CharacterDetailsState> = MutableStateFlow(CharacterDetailsState())
    val state = _state.asStateFlow()

    init {
        getCharacterData()
    }

    private fun getCharacterData() {
        viewModelScope.launch {
            showApi
                .getCharacterById(id = characterId)
                .map { response -> response.data.mapToCharacterModel() }
                .onSuccess { character ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            data = character
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = this.createSavedStateHandle()
                val context = checkNotNull(this[APPLICATION_KEY]) {
                    "Application context needed to create the ViewModel"
                }
                val api = KtorShowApi(KtorDependencies.provideHttpClient(context))
                CharacterDetailsViewModel(api, savedStateHandle)
            }
        }
    }
}
