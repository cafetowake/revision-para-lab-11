package lab3moviles.uvg.edu.gt.funcionamiento.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lab3moviles.uvg.edu.gt.data.data.DataStore.DataStoreManager

@Composable
fun LoginScreen(navController: NavController, dataStore: DataStoreManager) {
    var userName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.rick_and_morty_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar estado de carga
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    if (userName.isNotEmpty()) {
                        // Iniciar sincronización de datos y guardar el nombre
                        isLoading = true
                        CoroutineScope(Dispatchers.IO).launch {
                            dataSyncManager.syncData() // Sincronizar datos
                            dataStore.saveUserName(userName) // Guardar nombre
                            withContext(Dispatchers.Main) {
                                isLoading = false
                                navController.navigate("characters_list") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}



