package com.azamovhudstc.bookappwithcache.di

import android.content.Context
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.auth.AuthService
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.book.BookService
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .build()


    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://143.198.48.222:82")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    fun getBookService(retrofit: Retrofit): BookService = retrofit.create(BookService::class.java)


}