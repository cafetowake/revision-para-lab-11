package lab3moviles.uvg.edu.gt.data.network.dto.character

import kotlinx.serialization.Serializable
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity
import lab3moviles.uvg.edu.gt.data.dc.Person

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
)

fun CharacterDto.mapToCharacterModel(): Person{
    return Person(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
    )
}

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
    )
}