package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list

import lab3moviles.uvg.edu.gt.data.dc.Location

data class LocationsState(
    val isLoading: Boolean = true,
    val locations: List<Location> = emptyList(),
    val isError: Boolean = false
)