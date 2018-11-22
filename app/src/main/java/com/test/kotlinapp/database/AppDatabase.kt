package com.test.kotlinapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.test.kotlinapp.utils.Logger

const val DB_NAME = "KOTLIN_TEST_DB"

@Database(entities = [Project::class, Resource::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProjectDao(): ProjectDao
    abstract fun getResourceDao(): ResourceDao
    abstract fun getProjectResourceDao(): ProjectResourceDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            Logger.d("AppDatabase.getInstance() context = $context");
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DB_NAME)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}