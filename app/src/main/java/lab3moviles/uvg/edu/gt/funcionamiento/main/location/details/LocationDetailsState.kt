package lab3moviles.uvg.edu.gt.funcionamiento.main.location.details

import lab3moviles.uvg.edu.gt.data.dc.Location


data class LocationDetailsState(
    val data: Location? = null,
    val isLoading: Boolean = true
)