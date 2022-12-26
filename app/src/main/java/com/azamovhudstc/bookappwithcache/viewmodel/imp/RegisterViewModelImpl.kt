package com.azamovhudstc.bookappwithcache.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.RegisterUseCase
import com.azamovhudstc.bookappwithcache.usecase.auth.impl.RegisterUseCaseImpl
import com.azamovhudstc.bookappwithcache.utils.hasConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    val openVerifyScreenLiveData = MutableLiveData<Unit>()
    val notConnectionLiveData = MutableLiveData<Unit>()
    val messageLIveData = MutableLiveData<String>()
    val backLoginLiveData = MutableLiveData<Unit>()
    val changeButtonStatusLiveData = MutableLiveData<Boolean>()
    val progressLiveData = MutableLiveData<Boolean>()

    fun register(name: String, password: String, phone: String, lastName: String) {
        changeButtonStatusLiveData.value = false
        progressLiveData.value = true
        if (!hasConnection()) {
            notConnectionLiveData.value = Unit
            return
        } else {
            if (registerUseCase.check(name, password)) {
                registerUseCase.register(name, password, phone, lastName).onEach {
                    if (it) {
                        changeButtonStatusLiveData.value = true
                        messageLIveData.value = "registered in successfully"
                        progressLiveData.value = false
                        openVerifyScreenLiveData.value = Unit

                    } else {
                        progressLiveData.value = false
                        changeButtonStatusLiveData.value = true
                        messageLIveData.value = "Maydonlarda xatolik mavjud"
                    }

                }
            } else {
                progressLiveData.value = false

                messageLIveData.value = "Maydonlarda xatolik mavjud"

            }

        }
    }


    fun backLogin() {
        backLoginLiveData.value = Unit
    }


}