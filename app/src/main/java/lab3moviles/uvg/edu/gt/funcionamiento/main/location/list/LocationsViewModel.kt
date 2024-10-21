package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.repository.LocalLocationRepository
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class LocationsViewModel(
    private val locationRepository: LocationRepository
): ViewModel() {
    private var getDataJob: Job? = null
    private var _state = MutableStateFlow(LocationsState())
    val state = _state.asStateFlow()

    init {
        getLocations()
    }

    fun onEvent(event: LocationsEvent) {
        when (event) {
            LocationsEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            LocationsEvent.RetryClick -> {
                getLocations()
            }
        }
    }

    private fun getLocations() {
        getDataJob = viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    isError = false
                )
            }

            val locations = locationRepository.getLocations()

            _state.update { state ->
                state.copy(
                    isLoading = false,
                    locations = locations
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LocationsViewModel(
                    locationRepository = LocalLocationRepository()
                )
            }
        }
    }

}