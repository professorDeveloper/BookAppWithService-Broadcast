package com.azamovhudstc.bookappwithcache.data.local.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.azamovhudstc.bookappwithcache.data.mapping.NotificationMap
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.book.BooksResponseItem
import java.io.Serializable

@Entity
data class BookEntities(
    val id: Int,
    var author: String,
    var description: String,
    var fav: Boolean,
    var pageCount: Int,
    var title: String,
    @PrimaryKey(autoGenerate = true)
    val localId: Int,
    var state: Int
) : Serializable {
    fun toNotification(message: String): NotificationMap = NotificationMap(
        message = message,
        title = title,
    )

    fun toUpdate(): EditBookRequest = EditBookRequest(author, description, id, pageCount, title)
    fun toAdd(): AddBookRequest = AddBookRequest(author, description, pageCount, title)
    fun toDelete(): BooksResponseItem =
        BooksResponseItem(author, description, fav, id, pageCount, title)
}