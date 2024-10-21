package lab3moviles.uvg.edu.gt.repository

import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.dc.Location

class LocationRepository(private val locationDao: LocationDao) {
    suspend fun syncLocations(locations: List<Location>) {
        locationDao.insertAll(locations)
    }

    suspend fun getLocations(): List<Location> {
        return locationDao.getAll()
    }
}


