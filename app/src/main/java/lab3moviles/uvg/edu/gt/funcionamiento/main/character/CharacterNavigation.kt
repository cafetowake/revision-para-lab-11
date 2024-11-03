package lab3moviles.uvg.edu.gt.funcionamiento.main.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.details.characterDetailsScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.details.navigateToCharacterDetailsScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.CharactersDestination
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.charactersScreen

@Serializable
data object CharacterNavGraph

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharactersDestination
    ) {
        charactersScreen(
            onCharacterClick = navController::navigateToCharacterDetailsScreen
        )
        characterDetailsScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}