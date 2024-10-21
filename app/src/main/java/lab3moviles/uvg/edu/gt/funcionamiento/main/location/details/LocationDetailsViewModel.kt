package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import lab3moviles.uvg.edu.gt.data.repository.LocalLocationRepository
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
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.details.LocationDetailsDestination
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.details.LocationDetailsState
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class LocationDetailsViewModel(
    private val locationRepository: LocationRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val locationProfile = savedStateHandle.toRoute<LocationDetailsDestination>()
    private val _uiState: MutableStateFlow<LocationDetailsState> = MutableStateFlow(LocationDetailsState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocationData()
    }

    private fun getLocationData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }

            val location = locationRepository.getLocationById(locationProfile.locationId)

            _uiState.update { state ->
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
                LocationDetailsViewModel(
                    locationRepository = LocalLocationRepository(),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
