package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterDetailsScreen(
    characterId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = CharacterDetailsDestination(characterId = characterId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.characterDetailsScreen(
    onNavigateBack: () -> Unit
) {
    composable<CharacterDetailsDestination> {
        CharacterDetailsRoute(onNavigateBack = onNavigateBack)
    }
}