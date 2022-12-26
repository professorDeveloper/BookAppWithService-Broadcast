package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ErrorResponse(
    val message: String
): Serializable