package lab3moviles.uvg.edu.gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import lab3moviles.uvg.edu.gt.funcionamiento.navigation.AppNavigation
import lab3moviles.uvg.edu.gt.ui.theme.Lab3movilesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Lab3movilesTheme  {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}