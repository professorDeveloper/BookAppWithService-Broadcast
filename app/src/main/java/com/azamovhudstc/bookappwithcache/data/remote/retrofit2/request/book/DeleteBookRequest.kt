package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book


import com.google.gson.annotations.SerializedName

data class DeleteBookRequest(
    @SerializedName("bookId")
    val bookId: Int
)
