package com.azamovhudstc.bookappwithcache.usecase.book.imp

import androidx.lifecycle.LiveData
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.repo.BookRepository
import com.azamovhudstc.bookappwithcache.usecase.book.BookScreenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BookScreenUseCaseImp @Inject constructor(var bookRepositoryImpl: BookRepository) :
    BookScreenUseCase {
    override fun editBook(bookEntities: BookEntities) = flow<Unit> {
        var unit = Unit
        unit = bookRepositoryImpl.editBook(
            bookEntities.id,
            bookEntities.toUpdate(),
            bookEntities.localId
        )

        emit(unit)
    }.flowOn(Dispatchers.IO)


    override fun delete(deleteBookRequest: BookEntities): Flow<Unit> = flow {

        emit(bookRepositoryImpl.deleteBook(
            deleteBookRequest.localId,
            deleteBookRequest.id
        ))
    }.flowOn(Dispatchers.IO)

    override fun addBook(bookRequest: AddBookRequest): Flow<Unit> = flow {
        var unit = Unit

        unit = bookRepositoryImpl.addBook(bookRequest)

        emit(unit)
    }.flowOn(Dispatchers.IO)

    override fun reloadData(): Flow<Boolean> = flow {
        var boolean = false
        boolean = bookRepositoryImpl.reLoadLocalData()
        emit(boolean)

    }.flowOn(Dispatchers.IO)

    override fun getAllBooks(): Flow<LiveData<List<BookEntities>>> {
        return bookRepositoryImpl.getAllBook()
    }


}