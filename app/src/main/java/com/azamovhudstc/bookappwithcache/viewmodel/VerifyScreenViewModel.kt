package com.azamovhudstc.bookappwithcache.viewmodel

import androidx.lifecycle.MutableLiveData

interface VerifyScreenViewModel {
    val openHomeScreenLiveData:MutableLiveData<Unit>
    val errorMessageLiveData:MutableLiveData<String>
     val notConnectionLiveData: MutableLiveData<Unit>
    val changeButtonStatusLiveData : MutableLiveData<Boolean>
    val progressLiveData : MutableLiveData<Boolean>
    fun login(phone:String,password:String)
     fun verifySign(code:String)
     fun verifySignUp(code: String)
    fun register(name: String, password: String, phone: String, lastName: String)

}