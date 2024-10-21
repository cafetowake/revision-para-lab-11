package lab3moviles.uvg.edu.gt.data.data.DataStore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lab3moviles.uvg.edu.gt.data.data.local.Dao.CharacterDao
import lab3moviles.uvg.edu.gt.data.data.local.Dao.LocationDao
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity

@Database(entities = [CharacterEntity::class, LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rickmorty_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
