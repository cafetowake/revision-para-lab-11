package lab3moviles.uvg.edu.gt.repository

import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.dc.Person

class CharacterRepository(private val characterDao: CharacterDao) {
    suspend fun syncCharacters(characters: List<Person>) {
        characterDao.insertAll(characters)
    }

    suspend fun getCharacters(): List<Person> {
        return characterDao.getAll()
    }
}
