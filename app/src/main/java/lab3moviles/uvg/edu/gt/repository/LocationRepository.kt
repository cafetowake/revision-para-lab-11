package lab3moviles.uvg.edu.gt.repository

import kotlinx.coroutines.flow.Flow
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity
import lab3moviles.uvg.edu.gt.data.dc.Location

class LocationRepository @Inject constructor(
    private val locationDao: LocationDao
) {
    fun getAllLocations(): Flow<List<LocationEntity>> {
        return locationDao.getAllLocations()
    }

    fun getLocationById(id: Int): Flow<LocationEntity> {
        return locationDao.getLocationById(id)
    }

    suspend fun insertAll(locations: List<LocationEntity>) {
        locationDao.insertAll(locations)
    }
}



