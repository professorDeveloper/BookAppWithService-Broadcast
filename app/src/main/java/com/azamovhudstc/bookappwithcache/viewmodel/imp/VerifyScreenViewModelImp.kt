package com.azamovhudstc.bookappwithcache.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.VerifyUseCase
import com.azamovhudstc.bookappwithcache.usecase.auth.impl.VerifyUseCaseImpl
import com.azamovhudstc.bookappwithcache.utils.hasConnection
import com.azamovhudstc.bookappwithcache.viewmodel.VerifyScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyScreenViewModelImp @Inject constructor(
    private val verifyUSeCase: VerifyUseCase
) : VerifyScreenViewModel, ViewModel() {

    override val openHomeScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    override val notConnectionLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val changeButtonStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override fun login(phone: String, password: String) {
        progressLiveData.value = true
        if (!hasConnection()) {
            progressLiveData.value = false
            notConnectionLiveData.value = Unit
            return
        }

        viewModelScope.launch {
            verifyUSeCase.login(password, phone).flowOn(Dispatchers.Main).collect {
                it.onSuccess {
                    progressLiveData.value = false

                }
                it.onFailure {
                    progressLiveData.value = false
                    errorMessageLiveData.value = it.message
                }
            }
        }
    }

    override fun verifySign(code: String) {
        progressLiveData.value = true
        if (!hasConnection()) {
            progressLiveData.value = false
            notConnectionLiveData.value = Unit
            return
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                if (verifyUSeCase.verifySign(code).first()) {
                    viewModelScope.launch(Dispatchers.Main) {
                        openHomeScreenLiveData.value = Unit
                        progressLiveData.value = false

                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        changeButtonStatusLiveData.value = true

                        progressLiveData.value = false

                    }
                }

            }
        }

    }

    override fun verifySignUp(code: String) {
        if (!hasConnection()) {
            notConnectionLiveData.value = Unit
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                if (verifyUSeCase.verifySignUp(code).first()) {
                    viewModelScope.launch(Dispatchers.Main) {
                        openHomeScreenLiveData.value = Unit

                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        errorMessageLiveData.value = "Kod Hato !"

                    }
                }

            }
        }

    }

    override fun register(name: String, password: String, phone: String, lastName: String) {
        if (!hasConnection()) {
            notConnectionLiveData.value = Unit
            return
        } else {

            viewModelScope.launch(Dispatchers.IO) {
                if (verifyUSeCase.register(name, password, phone, lastName).first()) {
                    viewModelScope.launch(Dispatchers.Main) {
                        changeButtonStatusLiveData.value = true
                        progressLiveData.value = false

                    }
                }
            }
        }

    }
}
