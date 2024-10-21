package lab3moviles.uvg.edu.gt.data.data.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object {
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
    }

    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    val userName: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }

    suspend fun clearUserName() {
        dataStore.edit { preferences ->
            preferences.remove(USER_NAME_KEY)
        }
    }
}
