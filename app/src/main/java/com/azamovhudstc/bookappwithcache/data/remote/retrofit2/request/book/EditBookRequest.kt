package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book


import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EditBookRequest(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("title")
    val title: String
):Serializable {
    fun toEntity(): BookEntities =
        BookEntities(id, author, description, false, pageCount, title, 0, 2)
}