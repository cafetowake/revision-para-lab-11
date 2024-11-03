package lab3moviles.uvg.edu.gt.data.network.dto.location

import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDto(
    val data: LocationDto,
    val message: String,
    val status: Int
)