package lab3moviles.uvg.edu.gt.domain.repository

import lab3moviles.uvg.edu.gt.data.dc.Person

interface CharacterRepository {
    suspend fun initialSync(): Boolean
    suspend fun getCharacters(): List<Person>
    suspend fun getCharacterById(id: Int): Person
}