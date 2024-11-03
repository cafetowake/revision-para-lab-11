package lab3moviles.uvg.edu.gt.data.data.DataStore

import android.content.Context
import androidx.room.Room

object AppDatabaseFactory {
    fun create(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "rickandmorty.db"
        ).build()
    }
}