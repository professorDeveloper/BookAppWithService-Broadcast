package com.azamovhudstc.bookappwithcache.di

import com.azamovhudstc.bookappwithcache.usecase.auth.LoginUseCase
import com.azamovhudstc.bookappwithcache.usecase.auth.RegisterUseCase
import com.azamovhudstc.bookappwithcache.usecase.auth.VerifyUseCase
import com.azamovhudstc.bookappwithcache.usecase.auth.impl.LoginUseCaseImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.impl.RegisterUseCaseImpl
import com.azamovhudstc.bookappwithcache.usecase.auth.impl.VerifyUseCaseImpl
import com.azamovhudstc.bookappwithcache.usecase.book.BookScreenUseCase
import com.azamovhudstc.bookappwithcache.usecase.book.imp.BookScreenUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    fun bindVerifyUseCase(impl: VerifyUseCaseImpl):VerifyUseCase

    @Binds
    fun bindBookScreenUseCase(impl: BookScreenUseCaseImp): BookScreenUseCase
}