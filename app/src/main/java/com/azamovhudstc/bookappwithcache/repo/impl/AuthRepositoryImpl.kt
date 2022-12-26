package com.azamovhudstc.bookappwithcache.repo.impl

import com.azamovhudstc.bookappwithcache.data.local.database.appDatabase.AppDatabase
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.AuthRequest
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.VerifyRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.auth.AuthService
import com.azamovhudstc.bookappwithcache.repo.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthService,
    private val pref: AppReference,
) : AuthRepository {


    override fun login(
        password: String,
        phone: String
    ): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = authApi.login(AuthRequest.LoginRequest(password, phone))
        if (response.isSuccessful) {
            response.body()?.let {
                val token: String = response.body()!!.token
                pref.verifyToken = token!!
                emit(Result.success(Unit))
            }
        } else {
            emit(Result.failure(Exception(response.errorBody()?.toString())))
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun register(
        name: String,
        password: String,
        phone: String,
        lastName: String
    ) = flow {
        var response =
            authApi.register(AuthRequest.RegisterRequest(name, lastName, password, phone))
        if (response.isSuccessful) {
            if (response.body() != null) {
                val token: String = response.body()!!.token
                pref.verifyToken = token!!
                emit(true)
            }
            emit(false)
        }
        emit(false)

    }.flowOn(Dispatchers.IO)

    override fun verify( phone: String) = flow {
        var response = authApi.verify("Bearer ${pref.verifyToken}", VerifyRequest(phone))
        if (response.isSuccessful && response.body() != null) {
            val responseToken: String = response.body()!!.token
            pref.setToken(responseToken)
            emit(true)
        }

        emit(false)
    }.flowOn(Dispatchers.IO)

    override fun verifySign( phone: String): Flow<Boolean> = flow {
        var response = authApi.verifySign("Bearer ${pref.verifyToken}", VerifyRequest(phone))
        if (response.isSuccessful && response.body() != null) {
            val token: String = response.body()!!.token
            pref.setToken(token)
            emit(true)

        }

        emit(false)
    }.flowOn(Dispatchers.IO)

}