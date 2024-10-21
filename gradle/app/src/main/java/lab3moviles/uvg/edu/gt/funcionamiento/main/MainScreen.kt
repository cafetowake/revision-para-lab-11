package lab3moviles.uvg.edu.gt.funcionamiento.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lab3moviles.uvg.edu.gt.data.data.DataStore.DataStoreManager
import lab3moviles.uvg.edu.gt.funcionamiento.common.SplashScreen
import lab3moviles.uvg.edu.gt.funcionamiento.login.LoginScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.CharactersScreen

@Composable
fun MainScreen(navController: NavHostController, dataStoreManager: DataStoreManager) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController, dataStoreManager = dataStoreManager)
        }
        composable("login") {
            LoginScreen(navController = navController, dataStoreManager = dataStoreManager)
        }
        composable("characterList") {
            CharactersScreen(navController = navController)
        }
    }
}
