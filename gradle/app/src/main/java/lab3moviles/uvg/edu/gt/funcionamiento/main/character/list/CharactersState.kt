package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list

import lab3moviles.uvg.edu.gt.data.dc.Person

data class CharactersState(
    val isLoading: Boolean = true,
    val characters: List<Person> = emptyList(),
    val isError: Boolean = false
)