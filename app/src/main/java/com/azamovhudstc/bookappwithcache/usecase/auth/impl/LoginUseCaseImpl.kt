package com.azamovhudstc.bookappwithcache.usecase.auth.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(var authRepositoryImpl: AuthRepositoryImpl) :
    LoginUseCase {
    override fun login(password: String, phone: String): Flow<Result<Unit>> {
        return authRepositoryImpl.login(password, phone)
    }
}