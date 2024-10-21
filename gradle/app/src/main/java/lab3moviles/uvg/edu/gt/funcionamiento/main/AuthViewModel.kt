package lab3moviles.uvg.edu.gt.funcionamiento.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.repository.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val userName: StateFlow<String?> = authRepository.userName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun loginUser(name: String) {
        viewModelScope.launch {
            authRepository.loginUser(name)
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            authRepository.logoutUser()
        }
    }
}
