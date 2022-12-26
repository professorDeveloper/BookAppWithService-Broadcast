package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.auth


import com.google.gson.annotations.SerializedName
import java.io.Serializable

sealed class AuthResponse{
    data class RegisterResponse(
        @SerializedName("token")
        val token: String
    )

    data class LoginResponse(
        @SerializedName("token")
        val token: String
    ):Serializable

}