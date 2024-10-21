package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list

sealed interface CharactersEvent {
    data object ForceError: CharactersEvent
    data object RetryClick: CharactersEvent
}