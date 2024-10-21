package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharactersDestination

fun NavGraphBuilder.charactersScreen(
    onCharacterClick: (Int) -> Unit
) {
    composable<CharactersDestination> {
        CharactersRoute(onCharacterClick = onCharacterClick)
    }
}