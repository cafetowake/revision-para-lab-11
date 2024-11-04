package lab3moviles.uvg.edu.gt.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToModel
import lab3moviles.uvg.edu.gt.data.network.KtorShowApi
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.data.network.dto.character.mapToCharacterModel
import lab3moviles.uvg.edu.gt.domain.network.util.Result
import lab3moviles.uvg.edu.gt.domain.repository.CharacterRepository
import kotlin.coroutines.coroutineContext
import lab3moviles.uvg.edu.gt.data.network.dto.character.toEntity

class LocalCharacterRepository(
    private val characterDao: CharacterDao,
    private val api: KtorShowApi
) : CharacterRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            when (val apiResult = api.getAllCharacters()) {
                is Result.Success -> {
                    val charactersToInsert = apiResult.data.data.map { it.toEntity() }
                    characterDao.insertAll(charactersToInsert)
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

    override suspend fun getCharacters(): List<Person> {
        return try {
            val apiResult = api.getAllCharacters()
            when (apiResult) {
                is Result.Success -> {
                    val charactersFromApi = apiResult.data.data.map { it.mapToCharacterModel() }
                    characterDao.insertAll(apiResult.data.data.map { it.toEntity() })
                    charactersFromApi
                }
                is Result.Error -> {
                    println("API fetch failed, using local data: ${apiResult.error}")
                    characterDao.getAllCharacters().map { it.mapToModel() }
                }
            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println("Exception during getCharacters: $e")
            characterDao.getAllCharacters().map { it.mapToModel() }
        }
    }

    override suspend fun getCharacterById(id: Int): Person {
        delay(2000L)
        return characterDao.getCharacterById(id).mapToModel()
    }
}
