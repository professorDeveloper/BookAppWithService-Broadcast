package com.azamovhudstc.bookappwithcache.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities

interface EditScreenViewModel {
        val successBackLiveData: MutableLiveData<Unit>
        val errorLiveData: MutableLiveData<String>
        val progressStatusLiveData: MutableLiveData<Boolean>
        fun editBook(bookRequest: BookEntities)

}