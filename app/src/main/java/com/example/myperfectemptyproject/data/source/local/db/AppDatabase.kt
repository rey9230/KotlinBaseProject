package com.example.myperfectemptyproject.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myperfectemptyproject.data.source.local.model.LocalModel

@Database(entities = [LocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val myDao: MyDao

    companion object {
        const val DB_NAME = "AppDatabase.db"
    }

}