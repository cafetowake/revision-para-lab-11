package lab3moviles.uvg.edu.gt.funcionamiento.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.network.KtorShowApi
import lab3moviles.uvg.edu.gt.data.repository.LocalCharacterRepository
import lab3moviles.uvg.edu.gt.data.repository.LocalLocationRepository
import lab3moviles.uvg.edu.gt.di.AppDependencies
import lab3moviles.uvg.edu.gt.di.KtorDependencies
import lab3moviles.uvg.edu.gt.domain.repository.CharacterRepository
import lab3moviles.uvg.edu.gt.domain.repository.LocationRepository

class LoginViewModel(
    private val characterRepository: LocalCharacterRepository,
    private val locationRepository: LocalLocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onLogin() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (characterRepository.initialSync() && locationRepository.initialSync()) {
                _state.update { it.copy(
                    isLoading = false,
                    loginSuccessful = true
                ) }
            } else {
                _state.update { it.copy(
                    isLoading = false,
                    loginSuccessful = false
                ) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                val api = KtorShowApi(KtorDependencies.provideHttpClient(context))
                LoginViewModel(
                    characterRepository = LocalCharacterRepository(api),
                    locationRepository = LocalLocationRepository(api)
                )
            }
        }
    }
}
