package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.book


import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.DeleteBookRequest
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BooksResponseItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fav")
    var fav: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("title")
    val title: String
) : Serializable {
    fun toDeleteRequest(): DeleteBookRequest = DeleteBookRequest(id)
    fun toBookEntity(): BookEntities =
        BookEntities(id, author, description, fav, pageCount, title, 0, 0)

    fun toBookDelete(): BookEntities = BookEntities(id, author, description, fav, pageCount, title,state=-1, localId = 0)
}