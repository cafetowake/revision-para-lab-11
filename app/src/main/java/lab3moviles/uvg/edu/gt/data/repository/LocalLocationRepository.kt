package lab3moviles.uvg.edu.gt.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToModel
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.data.network.KtorShowApi
import lab3moviles.uvg.edu.gt.data.network.dto.location.mapToLocationModel
import lab3moviles.uvg.edu.gt.data.network.dto.location.toEntity
import lab3moviles.uvg.edu.gt.domain.repository.LocationRepository
import lab3moviles.uvg.edu.gt.domain.network.util.Result
import kotlin.coroutines.coroutineContext


class LocalLocationRepository(
    private val locationDao: LocationDao,
    private val api: KtorShowApi
): LocationRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            when (val apiResult = api.getAllLocations()) {
                is Result.Success -> {
                    val locationsToInsert = apiResult.data.data.map { it.toEntity() }
                    locationDao.insertAll(locationsToInsert)
                    true
                }
                is Result.Error -> {
                    coroutineContext.ensureActive()
                    println("Initial sync failed with error: ${apiResult.error}")
                    false
                }

            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println("Exception during initial sync: $e")
            false
        }
    }

    override suspend fun getLocations(): List<Location> {
        return try {
            val apiResult = api.getAllLocations()

            when (apiResult) {
                is Result.Success -> {
                    val locationsFromApi = apiResult.data.data.map { it.mapToLocationModel() }
                    locationDao.insertAll(apiResult.data.data.map { it.toEntity() })
                    locationsFromApi

                }

                is Result.Error -> {
                    println("API fetch failed, using local data: ${apiResult.error}")
                    locationDao.getAllLocations().map { it.mapToModel() }
                }
            }
        }catch (e: Exception) {
            coroutineContext.ensureActive()
            println("Exception during getLocations: $e")
            locationDao.getAllLocations().map { it.mapToModel() }
        }

        }


    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDao.getLocationById(id).mapToModel()
    }
}