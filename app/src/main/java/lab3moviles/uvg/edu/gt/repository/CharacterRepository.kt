package lab3moviles.uvg.edu.gt.repository

import kotlinx.coroutines.flow.Flow
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) {
    fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return characterDao.getAllCharacters()
    }

    fun getCharacterById(id: Int): Flow<CharacterEntity> {
        return characterDao.getCharacterById(id)
    }

    suspend fun insertAll(characters: List<CharacterEntity>) {
        characterDao.insertAll(characters)
    }
}
