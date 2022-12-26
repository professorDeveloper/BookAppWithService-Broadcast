package com.azamovhudstc.bookappwithcache.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities

@Dao
interface BookDao {

    @Query("SELECT * FROM BookEntities WHERE state=0 or state = 1 OR state = 2")
    fun getBooks(): LiveData<List<BookEntities>>

    @Query("SELECT * FROM BookEntities")
    suspend   fun getAllBooks(): List<BookEntities>


    @Query("DELETE FROM BookEntities")
    suspend fun clear()

    @Query("SELECT * FROM BookEntities WHERE localId = :id")
    suspend fun getBook(id: Int): BookEntities

    @Delete
    suspend fun delete(localData: BookEntities)

    @Insert
    suspend  fun insert(localData: BookEntities)

    @Insert
    suspend  fun insert(list: List<BookEntities>)

    @Update
    suspend fun update(localData: BookEntities)

    @Transaction
    suspend  fun updateWholeDB(newList: List<BookEntities>) {
        clear()
        insert(newList)
    }
}