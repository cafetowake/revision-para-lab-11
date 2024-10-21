package lab3moviles.uvg.edu.gt.data.data.DataStore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import lab3moviles.uvg.edu.gt.data.data.CharacterDb
import lab3moviles.uvg.edu.gt.data.data.LocationDb
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.repository.CharacterRepository
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class DataSyncManager(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) {
    suspend fun syncData() {
        delay(2000) // Simular sincronización inicial con un retraso de 2 segundos

        // Sincronizar personajes
        val characters = CharacterDb.getAllCharacters() // Obtener personajes desde el archivo JSON
        characterRepository.insertAll(characters) // Insertar en la base de datos Room

        // Sincronizar locaciones
        val locations = LocationDb.getAllLocations() // Obtener locaciones desde el archivo JSON
        locationRepository.insertAll(locations) // Insertar en la base de datos Room
    }
}

