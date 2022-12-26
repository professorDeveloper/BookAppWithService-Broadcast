package com.azamovhudstc.bookappwithcache.repo

import androidx.lifecycle.LiveData
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBook(): Flow<LiveData<List<BookEntities>>>
    suspend fun deleteBook(booksResponseItem: Int, id: Int)
    suspend fun addBook(bookRequest: AddBookRequest)
    suspend fun editBook(id: Int, requestData: EditBookRequest, localId: Int)
    suspend fun reLoadLocalData(): Boolean
    suspend fun internetState(): Flow<Boolean>

}