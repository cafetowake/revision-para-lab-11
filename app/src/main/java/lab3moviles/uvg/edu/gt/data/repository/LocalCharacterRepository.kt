package lab3moviles.uvg.edu.gt.data.repository


import kotlinx.coroutines.delay
import lab3moviles.uvg.edu.gt.data.data.CharacterDb
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.repository.CharacterRepository

class LocalCharacterRepository: CharacterRepository {
    private val characterDb = CharacterDb()

    override suspend fun getCharacters(): List<Person> {
        delay(2000L)
        return characterDb.getAllCharacters()
    }

    override suspend fun getCharacterById(id: Int): Person {
        delay(2000L)
        return characterDb.getCharacterById(id)
    }
}