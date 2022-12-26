package com.azamovhudstc.bookappwithcache.usecase.auth

import kotlinx.coroutines.flow.Flow


interface LoginUseCase {
    fun login( password:String, phone:String): Flow<Result<Unit>>
}