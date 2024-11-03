package lab3moviles.uvg.edu.gt.domain.repository

import lab3moviles.uvg.edu.gt.data.dc.Location

interface LocationRepository {
    suspend fun initialSync(): Boolean
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}