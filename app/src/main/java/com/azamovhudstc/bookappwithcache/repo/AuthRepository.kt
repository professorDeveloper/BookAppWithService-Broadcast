package com.azamovhudstc.bookappwithcache.repo

import androidx.lifecycle.LiveData
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.auth.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
     fun login(password: String, phone: String): Flow<Result<Unit>>
    fun register(name: String, password: String, phone: String, lastName: String): Flow<Boolean>
    fun verify( phone: String): Flow<Boolean>
        fun verifySign( phone: String): Flow<Boolean>

}