package com.azamovhudstc.bookappwithcache.di

import com.azamovhudstc.bookappwithcache.repo.AuthRepository
import com.azamovhudstc.bookappwithcache.repo.BookRepository
import com.azamovhudstc.bookappwithcache.repo.impl.AuthRepositoryImpl
import com.azamovhudstc.bookappwithcache.repo.impl.BookRepositoryImpl
import com.azamovhudstc.bookappwithcache.viewmodel.HomeScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.HomeScreenViewModelImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

        @Binds
        fun getAuthRepository(impl: AuthRepositoryImpl): AuthRepository

        @Binds
        fun getBookRepository(impl: BookRepositoryImpl): BookRepository


        @Binds
        fun getViewModel(impl: HomeScreenViewModelImp): HomeScreenViewModel

}