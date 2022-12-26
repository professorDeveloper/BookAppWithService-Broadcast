package com.azamovhudstc.bookappwithcache.usecase.auth.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(var authRepositoryImpl: AuthRepositoryImpl) :
    RegisterUseCase {
    override fun register(
        name: String,
        password: String,
        phone: String,
        lastName: String
    ): Flow<Boolean> = flow {
        if (check(name, password)) {
            emit(authRepositoryImpl.register(name, password, phone, lastName).first())
        } else {
            emit(false)
        }
    }.flowOn(Dispatchers.IO)

    override fun check(name: String, password: String): Boolean =
        (name.length > 3 && password.length > 3)


}