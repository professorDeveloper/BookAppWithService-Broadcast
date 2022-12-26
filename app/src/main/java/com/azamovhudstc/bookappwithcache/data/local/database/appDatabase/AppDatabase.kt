package com.azamovhudstc.bookappwithcache.data.local.database.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azamovhudstc.bookappwithcache.data.local.database.dao.BookDao
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities

@Database(entities = [BookEntities::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao():BookDao

    companion object {
        private var instance:AppDatabase? = null
        fun init(context: Context) {
            instance = Room.databaseBuilder(context,AppDatabase::class.java,"books_app.db")
                .build()
        }
        fun getInstance() = instance!!
    }
}