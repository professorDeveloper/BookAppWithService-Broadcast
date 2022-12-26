package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book


import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.google.gson.annotations.SerializedName

data class AddBookRequest(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("title")
    val title: String
){
    fun toAddRoom(): BookEntities =
        BookEntities(id = 0,author,description,fav = false,pageCount,title,0,1)
}