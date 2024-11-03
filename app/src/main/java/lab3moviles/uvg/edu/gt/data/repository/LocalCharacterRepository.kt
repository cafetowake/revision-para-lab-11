package lab3moviles.uvg.edu.gt.data.repository


import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import lab3moviles.uvg.edu.gt.data.data.CharacterDb
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToEntity
import lab3moviles.uvg.edu.gt.data.data.local.Entity.mapToModel
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.domain.repository.CharacterRepository
import kotlin.coroutines.coroutineContext


class LocalCharacterRepository(
    private val characterDao: CharacterDao
): CharacterRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            if (characterDao.getAllCharacters().isEmpty()) {
                val characterDb = CharacterDb()
                val charactersToInsert = characterDb.getAllCharacters().map { it.mapToEntity() }
                characterDao.insertAll(charactersToInsert)
            }
            return true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println(e)
            false
        }
    }

    override suspend fun getCharacters(): List<Person> {
        delay(2000L)
        return characterDao.getAllCharacters().map { it.mapToModel() }
    }

    override suspend fun getCharacterById(id: Int): Person {
        delay(2000L)
        return characterDao.getCharacterById(id).mapToModel()
    }
}