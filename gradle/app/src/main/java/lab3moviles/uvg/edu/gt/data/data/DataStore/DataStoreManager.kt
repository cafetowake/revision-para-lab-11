package lab3moviles.uvg.edu.gt.data.data.DataStore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

class DataStoreManager(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
    }

    fun saveUserName(name: String) {
        dataStore.edit { prefs ->
            prefs[USER_NAME_KEY] = name
        }
    }

    fun getUserName(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[USER_NAME_KEY] ?: ""
        }
    }

    fun clearUserName() {
        dataStore.edit { prefs ->
            prefs.remove(USER_NAME_KEY)
        }
    }
}
