package com.azamovhudstc.bookappwithcache.data.remote.request.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

sealed class AuthRequest {
    data class RegisterRequest(
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("phone")
        val phone: String
    ):Serializable

    data class LoginRequest(
        @SerializedName("password")
        val password: String,
        @SerializedName("phone")
        val phone: String
    ):Serializable


}

