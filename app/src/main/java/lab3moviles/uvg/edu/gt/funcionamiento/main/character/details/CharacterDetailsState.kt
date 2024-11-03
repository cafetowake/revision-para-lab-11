package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import lab3moviles.uvg.edu.gt.data.dc.Person

data class CharacterDetailsState(
    val data: Person? = null,
    val isLoading: Boolean = true
)