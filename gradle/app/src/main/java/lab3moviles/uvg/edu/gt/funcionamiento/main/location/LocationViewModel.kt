package lab3moviles.uvg.edu.gt.funcionamiento.main.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.data.LocationDb
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations

    init {
        syncLocations()
    }

    private fun syncLocations() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            val locationsFromDb = locationRepository.getLocations()
            if (locationsFromDb.isEmpty()) {
                locationRepository.syncLocations(LocationDb.getLocationsById())
            }
            _locations.value = locationRepository.getLocations()
            _isLoading.value = false
        }
    }
}
