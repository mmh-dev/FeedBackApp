package com.mmh.feedbackapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmh.feedbackapp.entities.FeedBack

@Database(entities = [FeedBack::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): Dao
}