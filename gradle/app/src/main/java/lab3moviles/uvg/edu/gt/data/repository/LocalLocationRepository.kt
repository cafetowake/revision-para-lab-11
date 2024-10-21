package lab3moviles.uvg.edu.gt.data.repository


import kotlinx.coroutines.delay
import lab3moviles.uvg.edu.gt.data.data.LocationDb
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class LocalLocationRepository: LocationRepository {
    private val locationDb = LocationDb()

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDb.getAllLocations()
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDb.getLocationById(id)
    }
}