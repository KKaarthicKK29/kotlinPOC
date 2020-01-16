package com.example.karthickmadasamy.myapplication.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Karthick.Madasamy on 12/27/2019.
 */

@Database(entities = arrayOf(FeederEntity::class), version = 9, exportSchema = false)
 abstract class DbHelper : RoomDatabase() {


    abstract  val feederDao: FeederDao

  /* override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        return null
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return null
    }*/

    fun clearAllTables() {

    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DbHelper? = null
        fun getInstance(context: Context): DbHelper {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DbHelper::class.java,
                        "feeder_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
