package com.azamovhudstc.bookappwithcache.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities

interface HomeScreenViewModel {
    val addBookLiveData: MediatorLiveData<Unit>
    val messageLiveData: MediatorLiveData<String>
    val statusLiveData:MutableLiveData<String>
    val getAllBooksLiveData: MutableLiveData<List<BookEntities>>
    val editBookLiveData: MediatorLiveData<BookEntities>
    val progressLiveData: MutableLiveData<Boolean>
    fun editBook(bookEntities: BookEntities)
    fun status()
    fun delete(deleteBookRequest: BookEntities)
    fun addBook()
    fun reloadData()
    fun getAllBooks()

}