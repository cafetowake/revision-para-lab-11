package lab3moviles.uvg.edu.gt.funcionamiento.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lab3moviles.uvg.edu.gt.funcionamiento.login.LoginScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.AuthViewModel
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.CharactersScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("characters") {
            CharactersScreen(navController, authViewModel)
        }
        composable("profile") {
            ProfileScreen(navController, authViewModel)
        }
    }
}
