package com.azamovhudstc.bookappwithcache.data.remote.request.auth


import com.google.gson.annotations.SerializedName

data class VerifyRequest(
    @SerializedName("code")
    val code: String
)