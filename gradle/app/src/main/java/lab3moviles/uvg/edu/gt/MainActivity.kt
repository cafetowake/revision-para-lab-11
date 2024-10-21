package lab3moviles.uvg.edu.gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import lab3moviles.uvg.edu.gt.data.data.DataStore.UserPreferences
import lab3moviles.uvg.edu.gt.funcionamiento.navigation.AppNavigation



class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            userPreferences = UserPreferences(this)

            val navController = rememberNavController()

            LaunchedEffect(key1 = Unit) {
                userPreferences.userName.collect { name ->
                    if (name != null) {
                        navController.navigate("characters")
                    } else {
                        navController.navigate("login")
                    }
                }
            }

            AppNavigation(navController = navController)
        }
    }
}


