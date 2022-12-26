package com.azamovhudstc.bookappwithcache.usecase.auth

import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    fun check(name: String, password: String): Boolean
    fun register(name: String, password: String, phone: String, lastName: String):Flow<Boolean>
}