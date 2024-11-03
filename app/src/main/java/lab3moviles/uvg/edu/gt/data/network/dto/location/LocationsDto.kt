package lab3moviles.uvg.edu.gt.data.network.dto.location

import kotlinx.serialization.Serializable

@Serializable
data class LocationsDto(
    val data: List<LocationDto>,
    val message: String,
    val status: Int
)