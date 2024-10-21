package lab3moviles.uvg.edu.gt.repository

import lab3moviles.uvg.edu.gt.data.data.DataStore.UserPreferences

class AuthRepository(private val userPreferences: UserPreferences) {
    suspend fun loginUser(name: String) {
        userPreferences.saveUserName(name)
    }

    suspend fun logoutUser() {
        userPreferences.clearUserName()
    }

    val userName = userPreferences.userName
}