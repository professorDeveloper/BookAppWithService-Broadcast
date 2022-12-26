package com.azamovhudstc.bookappwithcache.data.remote.response.book


import com.google.gson.annotations.SerializedName

data class AddBookResponse(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fav")
    val fav: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("title")
    val title: String
)