package com.azamovhudstc.bookappwithcache.data.remote.response.auth


import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("token")
    val token: String
)