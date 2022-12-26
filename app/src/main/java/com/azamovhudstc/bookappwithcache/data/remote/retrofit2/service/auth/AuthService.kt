package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.auth

import com.azamovhudstc.bookappwithcache.data.remote.request.auth.AuthRequest
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.VerifyRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.auth.AuthResponse
import com.azamovhudstc.bookappwithcache.data.remote.response.auth.VerifyResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/sign-up")
    suspend fun register(@Body data: AuthRequest.RegisterRequest): Response<AuthResponse.RegisterResponse>

    @POST("/auth/sign-in")
    suspend  fun login(@Body data: AuthRequest.LoginRequest): Response<AuthResponse.LoginResponse>

    @POST("/auth/sign-up/verify")
    suspend   fun verify(
        @Header("Authorization") authorization: String,
        @Body code: VerifyRequest
    ): Response<VerifyResponse>

    @POST("/auth/sign-in/verify")
    suspend   fun verifySign(
        @Header("Authorization") authorization: String,
        @Body code: VerifyRequest
    ): Response<VerifyResponse>


}