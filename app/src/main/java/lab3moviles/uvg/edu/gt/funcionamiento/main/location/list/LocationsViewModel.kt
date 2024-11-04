package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list

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
import lab3moviles.uvg.edu.gt.data.network.dto.location.mapToLocationModel
import lab3moviles.uvg.edu.gt.di.KtorDependencies
import lab3moviles.uvg.edu.gt.domain.Api.ShowApi
import lab3moviles.uvg.edu.gt.domain.network.util.map
import lab3moviles.uvg.edu.gt.domain.network.util.onError
import lab3moviles.uvg.edu.gt.domain.network.util.onSuccess

class LocationsViewModel(
    private val showApi: ShowApi
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
        viewModelScope.launch {
            showApi
                .getAllLocations()
                .map { response -> response.data.map { it.mapToLocationModel() } }
                .onSuccess { locations ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            locations = locations
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
                LocationsViewModel(api)
            }
        }
    }
}