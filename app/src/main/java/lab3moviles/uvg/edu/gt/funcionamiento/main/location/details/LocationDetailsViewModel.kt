package lab3moviles.uvg.edu.gt.funcionamiento.main.location.details

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
import lab3moviles.uvg.edu.gt.data.network.dto.location.mapToLocationModel
import lab3moviles.uvg.edu.gt.di.KtorDependencies
import lab3moviles.uvg.edu.gt.domain.Api.ShowApi
import lab3moviles.uvg.edu.gt.domain.network.util.map
import lab3moviles.uvg.edu.gt.domain.network.util.onError
import lab3moviles.uvg.edu.gt.domain.network.util.onSuccess

class LocationDetailsViewModel(
    private val showApi: ShowApi,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val locationId: Int = savedStateHandle["locationId"] ?: throw IllegalArgumentException("Location ID is missing")
    private val _uiState: MutableStateFlow<LocationDetailsState> = MutableStateFlow(LocationDetailsState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocationData()
    }

    private fun getLocationData() {
        viewModelScope.launch {
            showApi
                .getLocationById(id = locationId)
                .map { response -> response.data.mapToLocationModel() }
                .onSuccess { location ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            data = location
                        )
                    }
                }
                .onError {
                    _uiState.update {
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
                val savedStateHandle = createSavedStateHandle()
                val context = checkNotNull(this[APPLICATION_KEY]){ "Application context needed to create the ViewModel"}
                val api = KtorShowApi(KtorDependencies.provideHttpClient(context))

                LocationDetailsViewModel(api,savedStateHandle)
            }
        }
    }
}
