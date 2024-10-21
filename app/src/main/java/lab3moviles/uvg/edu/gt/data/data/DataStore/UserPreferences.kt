package lab3moviles.uvg.edu.gt.data.data.DataStore

import android.content.Context

class UserPreferences(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val IS_DATA_SYNCED_KEY = preferencesKey<Boolean>("IS_DATA_SYNCED")
    }


    fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
        }
    }


    fun getUserName(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_NAME_KEY]
    }


    fun setDataSynced(isSynced: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DATA_SYNCED_KEY] = isSynced
        }
    }


    fun isDataSynced(): Boolean? {
        val preferences = dataStore.data.first()
        return preferences[IS_DATA_SYNCED_KEY]
    }


    fun clearUserName() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
