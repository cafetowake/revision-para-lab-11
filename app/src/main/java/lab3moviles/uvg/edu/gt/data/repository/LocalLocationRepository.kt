package lab3moviles.uvg.edu.gt.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import lab3moviles.uvg.edu.gt.data.data.LocationDb
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToEntity
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToModel
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.domain.repository.LocationRepository
import kotlin.coroutines.coroutineContext


class LocalLocationRepository(
    private val locationDao: LocationDao
): LocationRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            if (locationDao.getAllLocations().isEmpty()) {
                val locationDb = LocationDb()
                val locationsToInsert = locationDb.getAllLocations().map { it.mapToEntity() }
                locationDao.insertAll(locationsToInsert)
            }
            true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println(e)
            false
        }
    }

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDao.getAllLocations().map { it.mapToModel() }
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDao.getLocationById(id).mapToModel()
    }
}