package com.azamovhudstc.bookappwithcache.data.remote.request.book


import com.google.gson.annotations.SerializedName

data class FavouriteBookRequest(
    @SerializedName("bookId")
    val bookId: Int
)