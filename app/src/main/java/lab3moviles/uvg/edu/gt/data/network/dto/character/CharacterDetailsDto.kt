package lab3moviles.uvg.edu.gt.data.network.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsDto(
    val data: CharacterDto,
    val message: String,
    val status: Int
)