package lab3moviles.uvg.edu.gt.data.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity
}