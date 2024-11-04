package lab3moviles.uvg.edu.gt.funcionamiento.login


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<LoginDestination> {
        LoginRoute(
            onSuccessfulLogin = onLoginClick,
        )
    }
}