package com.azamovhudstc.bookappwithcache.usecase.auth

import kotlinx.coroutines.flow.Flow

interface VerifyUseCase {
    fun login(phone: String, password: String):Flow<Result<Unit>>
    fun verifySign(code: String):Flow<Boolean>
    fun verifySignUp(code: String):Flow<Boolean>
    fun register(name: String, password: String, phone: String, lastName: String):Flow<Boolean>
}