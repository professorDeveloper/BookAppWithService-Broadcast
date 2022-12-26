package com.azamovhudstc.bookappwithcache.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import com.azamovhudstc.bookappwithcache.repo.impl.BookRepositoryImpl
import com.azamovhudstc.bookappwithcache.viewmodel.EditScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModelImpl @Inject constructor(private val bookScreenUseCaseImp: BookRepositoryImpl) :
    EditScreenViewModel, ViewModel() {
    override val successBackLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData()
    override val progressStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun editBook(bookRequest: BookEntities) {
        progressStatusLiveData.value = true


        if (checker(bookRequest.toUpdate())) {
            progressStatusLiveData.value = false
            viewModelScope.launch(Dispatchers.IO) {
                bookScreenUseCaseImp.editBook(
                    bookRequest.id,
                    bookRequest.toUpdate(),
                    bookRequest.localId
                )
                successBackLiveData.postValue(Unit)
            }
        } else {
            progressStatusLiveData.value = false

            errorLiveData.value = "Maydonlar Hato to`ldirilgan"
        }
    }

    private fun checker(bookRequest: EditBookRequest): Boolean {
        return bookRequest.author.length > 10 && bookRequest.description.length > 30

    }
}