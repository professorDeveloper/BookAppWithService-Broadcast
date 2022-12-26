package com.azamovhudstc.bookappwithcache.usecase.auth.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.VerifyUseCase
import com.azamovhudstc.bookappwithcache.viewmodel.VerifyScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class VerifyUseCaseImpl @Inject constructor(var repo: AuthRepositoryImpl) : VerifyUseCase {
    override fun login(phone: String, password: String): Flow<Result<Unit>> {
        return repo.login(password, phone)
    }

    override fun verifySign(code: String): Flow<Boolean> {
        return repo.verifySign(code)
    }

    override fun verifySignUp(code: String): Flow<Boolean> {
        return repo.verify(code)
    }

    override fun register(
        name: String,
        password: String,
        phone: String,
        lastName: String
    ): Flow<Boolean> {
        return repo.register(name, password, phone, lastName)
    }

}