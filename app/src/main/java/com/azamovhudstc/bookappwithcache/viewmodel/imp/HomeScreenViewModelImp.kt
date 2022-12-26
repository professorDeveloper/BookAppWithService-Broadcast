package com.azamovhudstc.bookappwithcache.viewmodel.imp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.*
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.usecase.book.imp.BookScreenUseCaseImp
import com.azamovhudstc.bookappwithcache.utils.hasConnection
import com.azamovhudstc.bookappwithcache.viewmodel.HomeScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModelImp @Inject constructor(private val bookScreenUseCaseImp: BookScreenUseCaseImp) :
    HomeScreenViewModel, ViewModel() {
    override val addBookLiveData: MediatorLiveData<Unit> = MediatorLiveData()
    override val messageLiveData: MediatorLiveData<String> = MediatorLiveData()
    override val statusLiveData: MutableLiveData<String> = MediatorLiveData()
    override var getAllBooksLiveData: MutableLiveData<List<BookEntities>> = MutableLiveData()
    override val editBookLiveData: MediatorLiveData<BookEntities> = MediatorLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var viewLiveData: LiveData<List<BookEntities>> = MutableLiveData()

    init {
        getAllBooks()
    }

    override fun editBook(bookEntities: BookEntities) {
    }

    override fun status() {
        viewModelScope.launch {
            if (hasConnection()) statusLiveData.value = "Online"
            else statusLiveData.value =
                "Offline"
        }

    }

    override fun delete(deleteBookRequest: BookEntities) {
        bookScreenUseCaseImp.delete(deleteBookRequest).launchIn(viewModelScope)

    }

    override fun addBook() {
        addBookLiveData.value = Unit
    }

    override fun reloadData() {
        try {
            if (hasConnection()) {
                status()
                viewModelScope.launch(Dispatchers.Main) {
                    progressLiveData.value = true
                    bookScreenUseCaseImp.reloadData().onEach {
                        progressLiveData.value = false

                    }.launchIn(viewModelScope)
                }
            } else {
                messageLiveData.value = "Iltimos Internetni yoqing !"
            }
            status()
        } catch (e: SocketTimeoutException) {
            Log.d("!@#", "reloadData: ${e.message}")
        }

    }

    override fun getAllBooks() {
        progressLiveData.value = true

        viewModelScope.launch(Dispatchers.Main) {
            bookScreenUseCaseImp.getAllBooks().onEach { it ->
                editBookLiveData.addSource(it) {
                    getAllBooksLiveData.value = it

                }
            }.launchIn(viewModelScope)
            progressLiveData.value = false

        }
    }
}