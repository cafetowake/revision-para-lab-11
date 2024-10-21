package lab3moviles.uvg.edu.gt.funcionamiento.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import lab3moviles.uvg.edu.gt.funcionamiento.login.LoginDestination
import lab3moviles.uvg.edu.gt.funcionamiento.login.loginScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.mainNavigationGraph
import lab3moviles.uvg.edu.gt.funcionamiento.main.navigateToMainGraph

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToMainGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )
        mainNavigationGraph(
            onLogOutClick = {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }
        )
    }
}