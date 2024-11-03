package lab3moviles.uvg.edu.gt.di

import android.content.Context
import lab3moviles.uvg.edu.gt.data.data.DataStore.AppDatabase
import lab3moviles.uvg.edu.gt.data.data.DataStore.AppDatabaseFactory

object AppDependencies {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: AppDatabaseFactory.create(context).also { database = it }
        }
    }
}