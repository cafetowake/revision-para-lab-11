package lab3moviles.uvg.edu.gt.funcionamiento.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uvg.ejercicioslabs.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lab3moviles.uvg.edu.gt.data.data.DataStore.DataStoreManager
import lab3moviles.uvg.edu.gt.data.data.DataStore.DataSyncManager

@Composable
fun SplashScreen(navController: NavController, dataStoreManager: DataStoreManager) {
    val userName by dataStoreManager.getUserName().collectAsState(initial = "")

    LaunchedEffect(key1 = userName) {
        delay(2000) // Simulación de la pantalla de carga
        if (userName.isEmpty()) {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("characterList") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // Aquí puedes mostrar un indicador de carga o una animación
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


