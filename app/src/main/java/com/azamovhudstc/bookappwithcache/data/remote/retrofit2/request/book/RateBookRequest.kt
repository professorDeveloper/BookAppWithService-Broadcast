package com.azamovhudstc.bookappwithcache.data.remote.request.book


import com.google.gson.annotations.SerializedName

data class RateBookRequest(
    @SerializedName("bookId")
    val bookId: Int,
    @SerializedName("isLike")
    val isLike: Boolean
)