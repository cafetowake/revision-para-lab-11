package lab3moviles.uvg.edu.gt.data.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity

@Dao
interface LocationDao {
    @Insert
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query("SELECT * FROM LocationEntity")
    suspend fun getAllLocations(): List<LocationEntity>

    @Query("SELECT * FROM LocationEntity WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationEntity
}