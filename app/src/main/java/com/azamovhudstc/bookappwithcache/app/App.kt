package com.azamovhudstc.bookappwithcache.app

import NetworkReceiver
import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import com.azamovhudstc.bookappwithcache.data.local.database.appDatabase.AppDatabase
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.service.BookService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        AppDatabase.init(this)
        instance = this
    }

}
