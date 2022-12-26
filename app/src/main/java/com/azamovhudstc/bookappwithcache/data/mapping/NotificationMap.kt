package com.azamovhudstc.bookappwithcache.data.mapping

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.book.BooksResponseItem
import java.io.Serializable

data class NotificationMap(

    var title: String,

    var message: String

) : Serializable