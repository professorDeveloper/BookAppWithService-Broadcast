package com.azamovhudstc.bookappwithcache.viewmodel

import androidx.lifecycle.MutableLiveData

interface LoginViewModel {
    val errorLiveData: MutableLiveData<String>
    fun login(phone: String, password: String)
    val changeButtonStatusLiveData :MutableLiveData<Boolean>
    val progressLiveData:MutableLiveData<Boolean>
    val notConnectionLiveData: MutableLiveData<Unit>
    val openVerifyScreenLiveData: MutableLiveData<Unit>

}