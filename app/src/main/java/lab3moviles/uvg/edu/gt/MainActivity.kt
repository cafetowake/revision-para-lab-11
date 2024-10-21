package lab3moviles.uvg.edu.gt

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lab3moviles.uvg.edu.gt.data.data.DataStore.AppDatabase
import lab3moviles.uvg.edu.gt.data.data.DataStore.UserPreferences
import lab3moviles.uvg.edu.gt.funcionamiento.common.SplashScreen
import lab3moviles.uvg.edu.gt.funcionamiento.login.LoginScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.MainViewModel
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.CharactersScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.profile.ProfileScreen
import lab3moviles.uvg.edu.gt.repository.CharacterRepository
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val db = AppDatabase.getDatabase(applicationContext)
            val characterDao = db.characterDao()
            val locationDao = db.locationDao()
            val characterRepository = CharacterRepository(characterDao)
            val locationRepository = LocationRepository(locationDao)


            viewModel = MainViewModel(characterRepository, locationRepository)


            userPreferences = UserPreferences(applicationContext)


            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController, userPreferences, viewModel) }
                composable("login") { LoginScreen(navController, userPreferences) }
                composable("characters") { CharactersScreen(navController) }
                composable("locations") { LocationsScreen(navController, viewModel) }
                composable("profile") { ProfileScreen(navController, userPreferences) }
            }
        }
    }
}

