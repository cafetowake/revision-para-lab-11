package lab3moviles.uvg.edu.gt.funcionamiento.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.R

@Composable
fun LoginRoute(
    onSuccessfulLogin: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {
    val state by viewModel.state.collectAsState()
    LoginScreen(
        isLoading = state.isLoading,
        loginSuccessful = state.loginSuccessful,
        onLoginClick = viewModel::onLogin,
        onSuccessfulLogin = onSuccessfulLogin,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LoginScreen(
    isLoading: Boolean,
    loginSuccessful: Boolean,
    onLoginClick: () -> Unit,
    onSuccessfulLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(key1 = loginSuccessful) {
        if (loginSuccessful) {
            onSuccessfulLogin()
        }
    }
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.rick_and_morty_logo), contentDescription = "Logo")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Nombre")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Iniciar sesión")
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
        Text(
            text = "Paula De León - 23202",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}