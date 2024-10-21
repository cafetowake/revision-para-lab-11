package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationsDestination

fun NavGraphBuilder.locationsScreen(
    onLocationClick: (Int) -> Unit
) {
    composable<LocationsDestination> {
        LocationsRoute(onLocationClick = onLocationClick)
    }
}