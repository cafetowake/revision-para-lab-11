package lab3moviles.uvg.edu.gt.funcionamiento.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lab3moviles.uvg.edu.gt.funcionamiento.main.AuthViewModel


@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val userName by authViewModel.userName.collectAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido, $userName", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.logoutUser()
            navController.navigate("login") {
                popUpTo("characters") { inclusive = true }
            }
        }) {
            Text("Cerrar sesión")
        }
    }
}


