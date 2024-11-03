package lab3moviles.uvg.edu.gt.data.network.dto.location

import kotlinx.serialization.Serializable
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity
import lab3moviles.uvg.edu.gt.data.dc.Location

@Serializable
data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationDto.mapToLocationModel(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun LocationDto.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}