package lab3moviles.uvg.edu.gt.funcionamiento.main.profile


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit
) {
    composable<ProfileDestination> {
        ProfileRoute(onLogOutClick = onLogOutClick)
    }
}