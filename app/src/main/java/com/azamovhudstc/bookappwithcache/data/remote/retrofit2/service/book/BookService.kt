package com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.book

import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.DeleteBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.request.book.FavouriteBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.auth.ErrorResponse
import com.azamovhudstc.bookappwithcache.data.remote.response.book.AddBookResponse
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.book.BooksResponse
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.response.book.BooksResponseItem
import retrofit2.Response
import retrofit2.http.*

interface BookService {

    @GET("/books")
    suspend fun getAllBooks(@Header("Authorization") token: String): Response<BooksResponse>

    @POST("/book")
    suspend fun addBook(
        @Header("Authorization") token: String,
        @Body addBookRequest: AddBookRequest
    ): Response<AddBookResponse>


    @PUT("/book")
    suspend fun editBook(
        @Header("Authorization") token: String,
        @Body data: EditBookRequest
    ): Response<BooksResponseItem>

    @HTTP(method = "DELETE", path = "/book", hasBody = true)
    suspend fun deleteBook(
        @Header("Authorization") token: String,
        @Body body: DeleteBookRequest
    ): Response<ErrorResponse>

    @POST("book/change-fav")
    suspend fun addFavouriteBook(
        @Header("Authorization") token: String,
        @Body favouriteBookRequest: FavouriteBookRequest
    ): Response<ErrorResponse>


}