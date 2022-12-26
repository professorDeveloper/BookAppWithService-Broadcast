package com.azamovhudstc.bookappwithcache.usecase.book

import androidx.lifecycle.LiveData
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import kotlinx.coroutines.flow.Flow

interface BookScreenUseCase {

    fun editBook(bookEntities: BookEntities):Flow<Unit>
    fun delete(deleteBookRequest: BookEntities):Flow<Unit>
    fun addBook(bookRequest: AddBookRequest):Flow<Unit>
    fun reloadData(): Flow<Boolean>
    fun getAllBooks(): Flow<LiveData<List<BookEntities>>>
}