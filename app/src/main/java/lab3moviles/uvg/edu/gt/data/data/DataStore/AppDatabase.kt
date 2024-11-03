package lab3moviles.uvg.edu.gt.data.data.DataStore

import androidx.room.Database
import androidx.room.RoomDatabase
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        LocationEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}